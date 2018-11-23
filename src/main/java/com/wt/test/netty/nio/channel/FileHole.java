package com.wt.test.netty.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author WuTian
 * @date 2018-11-23 15:42
 * @description
 */
public class FileHole {
    public static void main(String[] args) {
        try {
            File directory = new File(System.getProperty("java.io.tmpdir"));
            File file = File.createTempFile("holyshit", "txt",directory);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
            putData(0,byteBuffer,fileChannel);
            putData(5000000,byteBuffer,fileChannel);
            putData(50000,byteBuffer,fileChannel);
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putData(int position, ByteBuffer byteBuffer, FileChannel fileChannel) {
        String str = "*<-- location " + position;
        byteBuffer.clear();
        try {
            byteBuffer.put(str.getBytes("US-ASCII"));
            byteBuffer.flip();
            fileChannel.position(position);
            fileChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
