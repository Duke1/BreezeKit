package com.qfleng.cryptokit;

/**

 *  网络字节序(Big-Endian)
 *
 */
public class ByteHelper {

    public static byte[] int2BArray(int x) {
        return new byte[]{
                (byte) (x >> 24),
                (byte) (x >> 16),
                (byte) (x >> 8),
                (byte) (x)
        };
    }

    public static int bArray2Int(byte[] ba) {
        int result =0 ,bSize = ba.length;
        if(0== bSize || bSize > 4) return 0;
        for(int i = 0 ; i < bSize ;i++){
            result |= ((ba[i] & 0xff) << ((bSize-1-i)*8));
        }

        return result;
    }


    public static byte[] long2BArray(long x) {

        byte[] targets = new byte[8];

        targets[0] = (byte) ((x >> 56) & 0xff);
        targets[1] = (byte) ((x >> 48) & 0xff);
        targets[2] = (byte) ((x >> 40) & 0xff);
        targets[3] = (byte) ((x >> 32) & 0xff);
        targets[4] = (byte) ((x >> 24) & 0xff);
        targets[5] = (byte) ((x >> 16) & 0xff);
        targets[6] = (byte) ((x >> 8) & 0xff);
        targets[7] = (byte) ((x) & 0xff);

        return targets;
    }


    public static long bArray2Long(byte[] ba) {
        return (  (((long) ba[0] & 0xff) << 56)
                | (((long) ba[1] & 0xff) << 48)
                | (((long) ba[2] & 0xff) << 40)
                | (((long) ba[3] & 0xff) << 32)
                | (((long) ba[4] & 0xff) << 24)
                | (((long) ba[5] & 0xff) << 16)
                | (((long) ba[6] & 0xff) << 8)
                | (((long) ba[7] & 0xff)));
    }

}