package com.xxzhwx.core.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelConnector extends Thread {
  public static class ConnectInfo {
    private String host;
    private int port;

    public ConnectInfo(String host, int port) {
      this.host = host;
      this.port = port;
    }

    public String getHost() {
      return host;
    }

    public int getPort() {
      return port;
    }
  }

  public static interface ConnectHandler {
    void handleConnectSuccess(Session session);

    void handleConnectFail(ConnectInfo connInfo);
  }

  private volatile boolean running = true;
  private Selector selector = null;
  private ConnectHandler handler = null;

  private int pollNum = 0;

  public ChannelConnector() {
  }

  public void init(ConnectHandler handler) throws IOException {
    this.setName("Connector");

    this.selector = Selector.open();
    this.handler = handler;
  }

  public void connect(String host, int port) {
    try {
      SocketChannel channel = SocketChannel.open();
      channel.configureBlocking(false);
      channel.connect(new InetSocketAddress(host, port));

      channel.register(selector, SelectionKey.OP_CONNECT, new ConnectInfo(host, port));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (running) {
      select();
    }
  }

  private void select() {
    try {
      int numOfKeys = selector.select(10L);
      // dump(numOfKeys);
      if (numOfKeys == 0) {
        return;
      }

      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> it = keys.iterator();
      while (it.hasNext()) {
        SelectionKey key = it.next();
        it.remove();

        if (!key.isValid()) {
          System.out.println("Invalid key.");
          continue;
        }

        if (!key.isConnectable()) {
          throw new RuntimeException("Unknown key status.");
        }

        finishConnection(key);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void finishConnection(SelectionKey key) {
    SocketChannel channel = (SocketChannel) key.channel();
    try {
      if (channel.finishConnect()) {
        // success
        handler.handleConnectSuccess(new Session(channel));
      } else {
        // pending, not yet finished.
        // !!!impossible to come here!!!
      }

      // always cancel the key
      key.cancel();
    } catch (IOException e) {
      // fail, the channel will be closed by underlying system.
      // all keys of the channel will be canceled when it is closed.
      e.printStackTrace();

      ConnectInfo connInfo = (ConnectInfo) key.attachment();
      handler.handleConnectFail(connInfo);
    }
  }

  private void dump(int numOfSelectedKeys) {
    pollNum++;
    System.out.println("pollNum: " + pollNum);

    Set<SelectionKey> keys = selector.keys();
    System.out.println("key size: " + keys.size());
    System.out.println("selected keys size: " + numOfSelectedKeys);
  }

  public void shutdown() {
    running = false;

    try {
      selector.wakeup();
      selector.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
