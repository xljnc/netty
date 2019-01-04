package com.wt.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Auther: 埼玉
 * @Date: 2019/1/4 17:29
 * @Description:
 */
public class FirstByteBuf {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in action sucks.", CharsetUtil.UTF_8);
        ByteBuf copiedByteBuf = byteBuf.copy();
        System.out.println(copiedByteBuf.toString(CharsetUtil.UTF_8));
        copiedByteBuf.setByte(0,(byte)'J');
        System.out.println(copiedByteBuf.toString(CharsetUtil.UTF_8));
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }
}
