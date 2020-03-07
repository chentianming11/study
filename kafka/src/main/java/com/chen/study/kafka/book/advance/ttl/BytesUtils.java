package com.chen.study.kafka.book.advance.ttl;

/**
 * @author 陈添明
 * @date 2020/3/7
 */
public class BytesUtils {

    public static byte[] longToBytes(long res) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((res >> offset) & 0xff);
        }
        return buffer;
    }

    public static long bytesToLong(byte[] b) {
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values |= (b[i] & 0xff);
        }
        return values;
    }
}

