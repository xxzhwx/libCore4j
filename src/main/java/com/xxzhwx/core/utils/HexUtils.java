package com.xxzhwx.core.utils;

public final class HexUtils {
    private static final String HEX_CHARS = "0123456789ABCDEF";

    private HexUtils() {
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HexUtils.HEX_CHARS.charAt(b >>> 4 & 0x0F));
            sb.append(HexUtils.HEX_CHARS.charAt(b & 0x0F));
        }
        return sb.toString();
    }

    public static byte[] toBytes(String s) {
        if (s.length() % 2 == 1) {
            s = "0" + s;
        }

        byte[] bytes = new byte[s.length()];
        int j = 0;
        for (int i = 0, n = bytes.length; i < n; ++i) {
            bytes[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character.digit(s.charAt(j++), 16));
        }
        return bytes;
    }
}
