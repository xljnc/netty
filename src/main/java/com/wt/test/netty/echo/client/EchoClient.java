package com.wt.test.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;

import javax.swing.*;
import java.net.InetSocketAddress;

/**
 * @Auther: 埼玉
 * @Date: 2019/1/2 11:18
 * @Description:
 */
@Slf4j
public class EchoClient {

    public static void main(String[] args) {
        new EchoClient().start();
    }

    public void start() {
        int port = 8888;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EchoClientHandler clientHandler = new EchoClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("error occurs", e);
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (Exception e) {
                log.error("error occurs", e);
            }
        }
    }
}
