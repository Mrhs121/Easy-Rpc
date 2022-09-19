package com.hs.easyrpc.core.utils;

public class Utils {
    public static final int PACKET_TYPE_LOGIN = 0x1001;
    public static final int PACKET_TYPE_MESSAGE = 0x1002;

    public static final int INT_SIZE = 4;
    public static final int INT_TYPE = 4;  // 包的类型所占长度
    public static final int INT_NAME = 4;  // 消息的长度的信息的长度
    public static final int SHORT_SIZE = 2;


    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];

        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    public static int byte2int(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        // sout("test")
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
                | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }

}
