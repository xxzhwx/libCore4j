package com.xxzhwx.core.utils.crypt;

import com.xxzhwx.core.utils.HexUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public final class Md5Utils {
    private static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String gen(String s) {
        return gen(s.getBytes());
    }

    public static String gen(byte[] bytes) {
        messageDigest.update(bytes);
        return HexUtils.toHexString(messageDigest.digest());
    }

    public static String gen(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int numOfBytes = 0;
            while ((numOfBytes = in.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, numOfBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return HexUtils.toHexString(messageDigest.digest());
    }
}
