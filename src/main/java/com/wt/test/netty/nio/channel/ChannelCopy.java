package com.wt.test.netty.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Xljnc
 * @date 2018/11/22 19:49
 * @description
 */
public class ChannelCopy {
    public static void main(String[] args) throws IOException {
        ReadableByteChannel readChannel = Channels.newChannel(System.in);
        WritableByteChannel writeChannel = Channels.newChannel(System.out);
//        copyChannelOne(readChannel, writeChannel);
        copyChannelTwo(readChannel, writeChannel);

    }

    public static void copyChannelOne(ReadableByteChannel readChannel, WritableByteChannel writeChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 16);
        while (readChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.compact();
        }
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            writeChannel.write(byteBuffer);
        }
    }

    public static void copyChannelTwo(ReadableByteChannel readChannel, WritableByteChannel writeChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 16);
        while (readChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                writeChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }

}
