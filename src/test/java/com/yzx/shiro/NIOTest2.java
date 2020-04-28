package com.yzx.shiro;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOTest2 {

    //编码和解码
    @Test
    public void test6() throws CharacterCodingException {

        Charset charset = Charset.forName("GBK");
        //设置编码器
        CharsetEncoder ce = charset.newEncoder();
        //设置解码器
        CharsetDecoder cd = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("你好呀");
        charBuffer.flip();
        ByteBuffer byteBuffer = ce.encode(charBuffer);
        for (int i = 0; i < 6; i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        CharBuffer charBuffer1 = cd.decode(byteBuffer);
        System.out.println("ddd===" + charBuffer1.toString());

    }


    //分散读取和聚集写入
    @Test
    public void test5() throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\1.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {byteBuffer, byteBuffer1};
        fileChannel.read(buffers);

        //分散读取
        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }
        System.out.println("buff1==" + new String(buffers[0].array(), 0, buffers[0].limit()));
        System.out.println("buff2==" + new String(buffers[1].array(), 0, buffers[1].limit()));

        System.out.println("----------------");
        //聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("D:\\2.txt", "rw");
        FileChannel fileChannel1 = randomAccessFile1.getChannel();
        fileChannel1.write(buffers);

        fileChannel.close();
        fileChannel1.close();
    }


    //通道之间的数据传输(直接缓冲区)
    @Test
    public void test4() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\bbb.xlsx"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\1b.xlsx"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    //使用直接的方式读取文件中的缓冲区(内存映射文件)
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("d:/1.mkv"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/2.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer inMap = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMap = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

        byte dest[] = new byte[inMap.limit()];
        inMap.get(dest);//读取数据到缓存
        outMap.put(dest);//从缓存中写数据到指定的位置

        inChannel.close();
        outChannel.close();
    }


    //使用 NIO 读写文件(间接方式)
    @Test
    public void test2() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ByteChannel inc = null;
        ByteChannel ouc = null;
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fis = new FileInputStream("D:\\bbb.xlsx");
            fos = new FileOutputStream("D:\\1b.xlsx");
            inc = fis.getChannel();
            ouc = fos.getChannel();
            while ((inc.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                ouc.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ouc != null) {
                ouc.close();
            }
            if (inc != null) {
                inc.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }


    @Test
    public void nioTest() {
        String a = "abcde";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(a.getBytes());
        byteBuffer.flip();
        for (int i = 0; i <= 3; i++) {
            byte[] dst = new byte[3];
            byteBuffer.get(dst, 0, 2);
            System.out.println("获取数据==" + new String(dst, 0, 2));
            byteBuffer.rewind();
        }
        byteBuffer.clear();
    }
}
