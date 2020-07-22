package com.qfleng.cryptokit;

/**
 * 十六进制辅助
 */
public class HexHelper {

    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static char[] byteToHex(byte[] data, boolean toLowerCase) {
        return byteToHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    private static char[] byteToHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    public static byte[] hexToByte(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("HexHelper hexToByte() exception. ");
        }
        byte[] out = new byte[len >> 1];

        for (int i = 0, j = 0; j < len; i++) {
            int f = toInt(data[j]) << 4;
            j++;
            f = f | toInt(data[j]);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }


    public static int toInt(char ch) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("HexHelper toInt() exception. ");
        }
        return digit;
    }
}
