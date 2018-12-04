package com.wt.test.netty.nio.channel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * @author Xljnc
 * @date 2018/11/25 11:30
 * @description
 */
public class PipeWork {
    public static void main(String[] args) throws Exception {
        WritableByteChannel out = Channels.newChannel(System.out);
        Pipe pipe = Pipe.open();
        new Thread(new Worker(pipe.sink(), 10), "Worker Thread").start();
        ReadableByteChannel in = pipe.source();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while (in.read(byteBuffer) != -1) {
            byteBuffer.flip();
            out.write(byteBuffer);
            byteBuffer.compact();
        }
    }

    private static class Worker implements Runnable {
        WritableByteChannel writeChannel;
        int loops;
        Random random = new Random();

        Worker() {
        }

        Worker(WritableByteChannel writeChannel, int loops) {
            this.writeChannel = writeChannel;
            this.loops = loops;
        }


        @Override
        public void run() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            for (int i = 0; i < loops; i++) {
                createContent(byteBuffer);
                while (byteBuffer.hasRemaining()) {
                    try {
                        this.writeChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                this.writeChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void createContent(ByteBuffer byteBuffer) {
            String content = "Holy Shit " + random.nextInt() + "\r\n";
            byteBuffer.clear();
            try {
                byteBuffer.put(content.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byteBuffer.flip();
        }

    }

}
