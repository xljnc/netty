package com.wt.test.netty.nio.channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author WuTian
 * @date 2018-11-23 16:38
 * @description
 */
public class FileCompare {
    public static void main(String[] args) throws Exception{
        File file1 = new File("C:/Users/Administrator/Desktop/holyshit.txt");
        File file2 = new File("C:/Users/Administrator/Desktop/holyshit.txt");
        System.out.println(file1==file2);
        System.out.println(file1.equals(file2));
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(file1,"rw");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2,"rw");
        FileChannel fileChannel1 = randomAccessFile1.getChannel();
        FileChannel fileChannel2 = randomAccessFile2.getChannel();
        System.out.println(fileChannel1==fileChannel2);
        System.out.println(fileChannel1.equals(fileChannel2));
        System.out.println(fileChannel1.position());
        System.out.println(fileChannel2.position());
        fileChannel1.position(500);
        System.out.println(fileChannel1.position());
        System.out.println(fileChannel2.position());
    }
}
