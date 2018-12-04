package com.wt.test.netty.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Random;

/**
 * @author Xljnc
 * @date 2018/12/1 20:42
 * @description
 */
public class AsynchronousEchoClient {
    public static void main(String[] args) {

        try {
            final AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 80);
            socketChannel.connect(socketAddress, null, new CompletionHandler<Void, Object>() {
                @Override
                public void completed(Void result, Object attachment) {
                    Random random = new Random(1000);
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(("User " + random).getBytes("UTF-8"));
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Object>() {

                            @Override
                            public void completed(Integer result, Object attachment) {
                                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                socketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {

                                    @Override
                                    public void completed(Integer result, ByteBuffer attachment) {
                                        try {
                                            attachment.flip();
                                            System.out.println(new String(attachment.array(), 0, attachment.limit(), "utf-8"));
                                            socketChannel.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void failed(Throwable exc, ByteBuffer attachment) {
                                        exc.printStackTrace();
                                        try {
                                            socketChannel.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {

                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
