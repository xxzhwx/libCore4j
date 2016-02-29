package com.xxzhwx.testcase;

import com.xxzhwx.core.buffer.IoBuffer;

public class TestCase_IoBuffer implements TestCase {
  @Override
  public boolean run() {
    IoBuffer buf = IoBuffer.allocate(2, false);
    logBuf(buf);

    buf.setAutoExpand(true);
    buf.setAutoShrink(true);

    buf.putInt(1);
    buf.putFloat(0.1F);
    buf.putDouble(0.1);
    buf.putInt(1);
    logBuf(buf);

    buf.flip(); // flip 后可以读
    logBuf(buf);

    buf.getInt();
    buf.getFloat();
    buf.getDouble();
    logBuf(buf);

    buf.compact(); // compact 后可以继续写
    logBuf(buf);

    buf.putInt(2);
    buf.putFloat(0.2F);
    buf.putDouble(0.2);
    logBuf(buf);

    buf.clear();
    logBuf(buf);

    return true;
  }

  private static void logBuf(IoBuffer buf) {
    System.out.println("position:" + buf.position() + ",limit:" + buf.limit()
            + ",capacity:" + buf.capacity());
  }
}
