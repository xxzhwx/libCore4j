package com.xxzhwx.core.channel;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ChannelPoller extends Thread {
   public static interface Handler {
     void handleRead(Session session);
     void handleWrite(Session session);
   }

  private volatile boolean running = true;
  private Selector selector;
  private Handler handler;

  private final List<Session> newSessions = new ArrayList<>();

  public ChannelPoller() {
  }

  public void init(Handler handler) throws IOException {
    this.setName("Selector");

    this.selector = Selector.open();
    this.handler = handler;
  }

  public void registerNewSession(Session session) {
    synchronized (newSessions) {
      newSessions.add(session);
    }
  }

  private List<Session> takeNewSessions() {
    List<Session> sessions = new ArrayList<>();
    synchronized (newSessions) {
      sessions.addAll(newSessions);
      newSessions.clear();
    }
    return sessions;
  }

  @Override
  public void run() {
    while (running) {
      registerSessions();
      poll();
    }
  }

  private void registerSessions() {
    try {
      List<Session> sessions = takeNewSessions();
      for (Session session : sessions) {
        SocketChannel channel = session.getChannel();
        channel.configureBlocking(false);
        channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        channel.setOption(StandardSocketOptions.SO_RCVBUF, 65536);
        channel.setOption(StandardSocketOptions.SO_SNDBUF, 65536);

        SelectionKey key = channel.register(selector, SelectionKey.OP_READ, session);
        session.setSelectionKey(key);

        System.out.println("Read from " + session.getId() + "/" + session.getIp());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void poll() {
    try {
      int numOfKeys = selector.select(10L);
      if (numOfKeys == 0) {
        return;
      }

      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> it = keys.iterator();
      while (it.hasNext()) {
        SelectionKey key = it.next();
        it.remove();

        if (!key.isValid()) {
          continue;
        }

        Session session = (Session) key.attachment();
        if (session.isClosed()) {
          continue;
        }

        if (key.isWritable()) {
          handleWrite(key, session);
        }
        else if (key.isReadable()) {
          handleRead(session);
        }
        else {
          throw new RuntimeException("Unknown key status.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleWrite(SelectionKey key, Session session) {
    key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);

    handler.handleWrite(session);
  }

  private void handleRead(Session session) {
    handler.handleRead(session);

    //! Maybe it is better to handle read directly, like:
    // session.read();
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
