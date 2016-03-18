package com.xxzhwx.core.utils;

public final class ArrayUtils {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];

    public static byte min(byte[] values, byte defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        byte minValue = Byte.MAX_VALUE;
        for (byte value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static short min(short[] values, short defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        short minValue = Short.MAX_VALUE;
        for (short value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static int min(int[] values, int defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        int minValue = Integer.MAX_VALUE;
        for (int value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static long min(long[] values, long defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        long minValue = Long.MAX_VALUE;
        for (long value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static byte max(byte[] values, byte defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        byte maxValue = Byte.MIN_VALUE;
        for (byte value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static short max(short[] values, short defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        short maxValue = Short.MIN_VALUE;
        for (short value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static int max(int[] values, int defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static long max(long[] values, long defValue) {
        if (isNullOrEmpty(values)) {
            return defValue;
        }

        long maxValue = Long.MIN_VALUE;
        for (long value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static boolean startsWith(byte[] values, byte[] subValues) {
        if (isNullOrEmpty(subValues)) {
            return true;
        }
        if (isNullOrEmpty(values)) {
            return false;
        }
        if (values.length < subValues.length) {
            return false;
        }

        for (int i = 0; i < subValues.length; ++i) {
            if (values[i] != subValues[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean startsWith(short[] values, short[] subValues) {
        if (isNullOrEmpty(subValues)) {
            return true;
        }
        if (isNullOrEmpty(values)) {
            return false;
        }
        if (values.length < subValues.length) {
            return false;
        }

        for (int i = 0; i < subValues.length; ++i) {
            if (values[i] != subValues[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean startsWith(int[] values, int[] subValues) {
        if (isNullOrEmpty(subValues)) {
            return true;
        }
        if (isNullOrEmpty(values)) {
            return false;
        }
        if (values.length < subValues.length) {
            return false;
        }

        for (int i = 0; i < subValues.length; ++i) {
            if (values[i] != subValues[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean startsWith(long[] values, long[] subValues) {
        if (isNullOrEmpty(subValues)) {
            return true;
        }
        if (isNullOrEmpty(values)) {
            return false;
        }
        if (values.length < subValues.length) {
            return false;
        }

        for (int i = 0; i < subValues.length; ++i) {
            if (values[i] != subValues[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(byte[] values, byte valueToFind) {
        return indexOf(values, valueToFind) != -1;
    }

    public static boolean contains(short[] values, short valueToFind) {
        return indexOf(values, valueToFind) != -1;
    }

    public static boolean contains(int[] values, int valueToFind) {
        return indexOf(values, valueToFind) != -1;
    }

    public static boolean contains(long[] values, long valueToFind) {
        return indexOf(values, valueToFind) != -1;
    }

    public static int indexOf(byte[] values, byte valueToFind) {
        if (isNullOrEmpty(values)) {
            return -1;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] == valueToFind) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(short[] values, short valueToFind) {
        if (isNullOrEmpty(values)) {
            return -1;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] == valueToFind) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(int[] values, int valueToFind) {
        if (isNullOrEmpty(values)) {
            return -1;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] == valueToFind) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(long[] values, long valueToFind) {
        if (isNullOrEmpty(values)) {
            return -1;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] == valueToFind) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isNullOrEmpty(byte[] values) {
        return values == null || values.length == 0;
    }

    public static boolean isNullOrEmpty(short[] values) {
        return values == null || values.length == 0;
    }

    public static boolean isNullOrEmpty(int[] values) {
        return values == null || values.length == 0;
    }

    public static boolean isNullOrEmpty(long[] values) {
        return values == null || values.length == 0;
    }
}
