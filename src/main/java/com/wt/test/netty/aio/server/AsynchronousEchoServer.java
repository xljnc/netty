package com.wt.test.netty.aio.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
 * @author Xljnc
 * @date 2018/12/1 16:45
 * @description
 */
public class AsynchronousEchoServer {

    public static void main(String[] args) {
        try {
            AsynchronousServerSocketChannel asChannel = AsynchronousServerSocketChannel.open();
            SocketAddress socketAddress = new InetSocketAddress(80);
            asChannel.bind(socketAddress);
            asChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(final AsynchronousSocketChannel client, Object attachment) {
                    asChannel.accept(null, this);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    client.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer resultNum, ByteBuffer attachment) {
                            attachment.flip();
                            try {
                                System.out.println("Get data from:" + new String(attachment.array(), 0, attachment.limit(), "utf-8"));
                                Future<Integer> result = client.write(byteBuffer);
                                result.get();
                                client.close();
                                attachment = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                            attachment = null;
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
