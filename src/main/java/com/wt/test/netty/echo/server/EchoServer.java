package com.wt.test.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author Xljnc
 * @date 2019/1/1 22:10
 * @description
 */
@Slf4j
public class EchoServer {

    public static void main(String[] args) {
        new EchoServer().start();
    }

    public void start() {
        int port = 8888;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EchoServerHandler echoServerHandler = new EchoServerHandler();
        ServerBootstrap server = new ServerBootstrap();
        try {
            server.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(echoServerHandler);
                }
            });
            ChannelFuture channelFuture = server.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("error occurs.", e);
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (Exception e) {
                log.error("error occurs.", e);
            }
        }

    }
}
