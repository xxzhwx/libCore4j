package com.xxzhwx.core.channel;

import com.xxzhwx.core.buffer.IoBuffer;
import com.xxzhwx.core.utils.SocketUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Session {
  public static interface Handler {
    void handlePacket(Session session, ByteBuffer buffer);

    void handleDestroyed(Session session);
  }

  public static final int MAX_LENGTH = 65535;
  private static final int LEN_SIZE = 4;

  private int id;
  private SocketChannel channel;
  private SelectionKey key;

  private ByteBuffer readBuffer;
  private IoBuffer writeBuffer;

  private String ip;

  private Handler handler;

  public Session(SocketChannel channel) {
    this.channel = channel;
    this.ip = SocketUtils.getIp(channel);

    readBuffer = ByteBuffer.allocate(MAX_LENGTH);
    writeBuffer = IoBuffer.allocate(1024);
    writeBuffer.setAutoExpand(true);
    writeBuffer.setAutoShrink(true);
  }

  public Session setId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }

  public Session setHandler(Handler handler) {
    this.handler = handler;
    return this;
  }

  public SocketChannel getChannel() {
    return channel;
  }

  public String getIp() {
    return ip;
  }

  void setSelectionKey(SelectionKey key) {
    this.key = key;
  }

  SelectionKey getSelectionKey() {
    return key;
  }

  public ByteBuffer getReadBuffer() {
    return readBuffer;
  }

  public void read() {
    //TODO
  }

  public void write(IoBuffer src) {
    synchronized (writeBuffer) {
      writeBuffer.put(src);
    }

    key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
  }

  public int flush() {
    int writeBytes = 0;

    synchronized (writeBuffer) {
      // 准备读
      writeBuffer.flip();

      try {
        writeBytes = channel.write(writeBuffer.buf());
      } catch (IOException e) {
        e.printStackTrace();
        destroy();
      }

      // 数据没写完就继续写轮询
      if (writeBuffer.hasRemaining())
        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);

      // 准备写
      writeBuffer.compact();
    }

    return writeBytes;
  }

  public void destroy() {
    if (key != null) {
      key.cancel();
      key = null;
    }

    if (channel != null) {
      try {
        channel.close();
        channel = null;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    handler.handleDestroyed(this);
  }

  public boolean isClosed() {
    return channel == null;
  }
}
