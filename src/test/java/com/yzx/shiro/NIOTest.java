package com.yzx.shiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NIOTest {

    //NIO使用测试
    /*public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        ReadableByteChannel src = Channels.newChannel(url.openStream());
        WritableByteChannel dest = new FileOutputStream("D:\\index.html").getChannel();
        dump(src,dest);
        System.out.println("结束。。");
    }*/

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\bbb.xlsx"));
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\cc.xlsx");
        FileChannel fileIn = fileInputStream.getChannel();
        FileChannel fileOut = fileOutputStream.getChannel();
        fileDeal(fileIn,fileOut);
        System.out.println("文件读写结束！");
    }

    public static void dump(ReadableByteChannel src, WritableByteChannel dest){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            ReadableByteChannel srcCH = src;
            WritableByteChannel destCH = dest;
            while (srcCH.read(byteBuffer) != -1){
                byteBuffer.flip();
                destCH.write(byteBuffer);
                byteBuffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  void fileDeal(FileChannel inFile,FileChannel outFile){
        ByteBuffer buffer  = ByteBuffer.allocate(1024);//设置缓存的大小
        try{
           while ((inFile.read(buffer)) != -1) {
               buffer.flip();//将positon 设置为0 ，将limit 设置围殴position 之前的位置
               outFile.write(buffer);
               buffer.clear();//将position 设置为0 limit 设置为capacity 的大小
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
