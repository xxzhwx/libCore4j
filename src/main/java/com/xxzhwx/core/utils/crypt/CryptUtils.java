package com.xxzhwx.core.utils.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public final class CryptUtils {
    private static final String KEY_ALGORITHM = "AES"; //密钥算法
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY_TEXT = "1234567890123456";
    private static Key key = null;
    private static Cipher cipher = null;

    private static final String CHARSET_NAME = "utf-8";

    static {
        try {
            key = new SecretKeySpec(KEY_TEXT.getBytes(CHARSET_NAME), KEY_ALGORITHM);
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(CHARSET_NAME)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
