package com.xxzhwx.core.utils;

import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class SocketUtils {
    public static String getIp(SocketChannel channel) {
        if (channel == null) {
            return null;
        }

        Socket socket = channel.socket();
        if (socket == null) {
            return null;
        }

        InetAddress addr = socket.getInetAddress();
        if (addr == null) {
            return null;
        }

        return addr.getHostAddress();
    }
}
