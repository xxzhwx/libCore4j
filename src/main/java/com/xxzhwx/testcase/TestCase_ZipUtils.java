package com.xxzhwx.testcase;

import com.xxzhwx.core.utils.ZipUtils;

import java.io.UnsupportedEncodingException;

public class TestCase_ZipUtils implements TestCase {
    @Override
    public boolean run() {
        String string = "你好，世界！世界，你好！";
        System.out.println("string1: " + string);

        try {
            byte[] raw = string.getBytes("utf-8");
            System.out.println("raw bytes length: " + raw.length);

            byte[] compressed = ZipUtils.compress(raw);
            System.out.println("compressed bytes length: " + compressed.length);

            byte[] decompressed = ZipUtils.decompress(compressed);
            System.out.println("decompressed bytes length: " + decompressed.length);

            System.out.println("string2: " + new String(decompressed, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
