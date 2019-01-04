package com.wt.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Auther: 埼玉
 * @Date: 2019/1/4 18:02
 * @Description:
 */
public class ReadWriteByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in action sucks.", CharsetUtil.UTF_8);
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        byteBuf.writeByte((byte) '?');
        System.out.println((char) byteBuf.readByte());
        System.out.println(readerIndex == byteBuf.readerIndex());
        System.out.println(writerIndex != byteBuf.writerIndex());

    }
}
