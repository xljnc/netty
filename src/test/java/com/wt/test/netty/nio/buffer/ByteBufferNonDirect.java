package com.wt.test.netty.nio.buffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WuTian
 * @date 2018-12-04 9:45
 * @description
 */
public class ByteBufferNonDirect {
    public static void main(String[] args) throws InterruptedException {
        List<ByteBuffer> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            list.add(byteBuffer);
            Thread.sleep(1000);
        }
    }
}
