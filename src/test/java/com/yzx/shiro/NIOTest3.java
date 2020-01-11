package com.yzx.shiro;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOTest3 {

    //客户端
    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9980));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\BaiduNetdiskDownload\\07 NIO\\nio\\nio\\source\\nio-day02\\src\\com\\atguigu\\nio\\TestBlockingNIO.java"), StandardOpenOption.READ);

        while(fileChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9980));
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\1121.java"),StandardOpenOption.READ,StandardOpenOption.CREATE,StandardOpenOption.WRITE);

        SocketChannel socketChannel = serverSocketChannel.accept();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(socketChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
