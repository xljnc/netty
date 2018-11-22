package com.wt.test.netty.nio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author WuTian
 * @date 2018-11-21 10:47
 * @description
 */
@Slf4j
public class NioServer {

    private static ServerSocketChannel server;

    public static void main(String[] args) {
        runServer();
    }

    private static void runServer() {
        try {
            server = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(80);
            server.bind(inetSocketAddress);
            server.configureBlocking(false);
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            stopServer();
        }
    }

    private static void stopServer() {
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
