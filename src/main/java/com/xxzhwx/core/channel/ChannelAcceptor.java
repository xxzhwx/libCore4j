package com.xxzhwx.core.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelAcceptor extends Thread {
  public static interface AcceptHandler {
    void handleAccept(Session session);
  }

  private volatile boolean running = true;
  private ServerSocketChannel serverChannel = null;
  private Selector selector = null;
  private AcceptHandler handler = null;

  public ChannelAcceptor() {
  }

  public void init(int port, AcceptHandler handler) throws IOException {
    this.setName("Acceptor");
    this.handler = handler;

    serverChannel = ServerSocketChannel.open();
    serverChannel.configureBlocking(false);
    serverChannel.socket().setReuseAddress(true);
    serverChannel.socket().bind(new InetSocketAddress(port));

    selector = Selector.open();
    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
  }

  @Override
  public void run() {
    while (running) {
      acceptNewChannels();
    }
  }

  private void acceptNewChannels() {
    try {
      selector.select();
      Set<SelectionKey> keys = selector.selectedKeys();

      Iterator<SelectionKey> it = keys.iterator();
      while (it.hasNext()) {
        SelectionKey key = it.next();
        it.remove();

        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        handler.handleAccept(new Session(sc));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
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
      closeServerSocket();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void closeServerSocket() throws IOException {
    serverChannel.socket().close();
    serverChannel.close();
    serverChannel = null;
  }
}
