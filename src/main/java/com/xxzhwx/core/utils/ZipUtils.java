package com.xxzhwx.core.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class ZipUtils {
    private static final int BUFFER_SIZE = 512;

    public static byte[] compress(byte[] input) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();

        byte[] buf = new byte[BUFFER_SIZE];
        while (!deflater.finished()) {
            int numOfBytes = deflater.deflate(buf);
            bos.write(buf, 0, numOfBytes);
        }

        return bos.toByteArray();
    }

    public static byte[] decompress(byte[] input) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Inflater inflater = new Inflater();
        inflater.setInput(input);

        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while (!inflater.finished()) {
                int numOfBytes = inflater.inflate(buf);
                bos.write(buf, 0, numOfBytes);
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
            return input;
        }

        return bos.toByteArray();
    }
}
