package com.xxzhwx.core.utils.crypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public final class CryptKeyGen {
    public static String genKey(String keyAlgorithm) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(keyAlgorithm);
            kg.init(128);

            SecretKey secretKey = kg.generateKey();
            System.out.println(new String(secretKey.getEncoded()));

            String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("gen key:" + key);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
