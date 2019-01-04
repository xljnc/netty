package com.wt.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Auther: 埼玉
 * @Date: 2019/1/4 17:43
 * @Description:
 */
public class GetSetByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in action sucks.", CharsetUtil.UTF_8);
        System.out.println((char) byteBuf.getByte(0));
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        byteBuf.setByte(0, 'j');
        System.out.println((char) byteBuf.getByte(0));
        System.out.println(readerIndex == byteBuf.readerIndex());
        System.out.println(writerIndex == byteBuf.writerIndex());
    }
}
