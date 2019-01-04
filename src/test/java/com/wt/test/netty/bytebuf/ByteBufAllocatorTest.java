package com.wt.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @Auther: 埼玉
 * @Date: 2019/1/4 21:52
 * @Description:
 */
public class ByteBufAllocatorTest {
    public static void main(String[] args) {
        Channel channel = new NioServerSocketChannel();
        ByteBufAllocator allocator = channel.alloc();
        ByteBuf byteBuf = allocator.buffer();
        System.out.println(byteBuf.refCnt());
        byteBuf.release();
        System.out.println(byteBuf.refCnt());
    }
}
