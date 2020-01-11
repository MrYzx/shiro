package com.yzx.shiro;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;

/**
 * java 中 NIO2 中的基本知识
 */
public class PathTest {

    @Test
    public void test7() throws IOException {
        Path path = Paths.get("d:\\www.txt");
        //通过 Files 获取通道
        SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,StandardOpenOption.READ);
        DirectoryStream<Path> directoryStreams = Files.newDirectoryStream(Paths.get("d:\\"));
        for(Path path1 : directoryStreams){
            System.out.println("path=="+path1);
        }
    }

    //FiLes 中基本方法的使用
    @Test
    public void test6() throws IOException {
        Path path = Paths.get("d:\\www.txt");
        boolean flag = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println("flag==="+flag);
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path,BasicFileAttributes.class,LinkOption.NOFOLLOW_LINKS);
        System.out.println(basicFileAttributes.creationTime());
        System.out.println(basicFileAttributes.lastModifiedTime());
        System.out.println(basicFileAttributes.size());

        //设置文件的查看属性
        DosFileAttributeView fileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        fileAttributeView.setHidden(false);
    }

    @Test
    public void test5() throws IOException {
        Path path = Paths.get("d:\\aaaa.txt");
        Path path1 = Paths.get("d:\\a\\ss.txt");
        System.out.println("fileSize=="+ Files.size(path));
        Files.move(path,path1,StandardCopyOption.ATOMIC_MOVE);
    }

    @Test
    public void test4() throws IOException {
        Path path = Paths.get("d:\\a\\bb");
        //创建目录
        Files.createDirectories(path);
        //创建文件
        Files.createFile(Paths.get("d:\\aaaa.txt"));
        //删除文件
        Files.deleteIfExists(Paths.get("d:\\index2.html"));
    }

    //java 中 Files 的使用
    @Test
    public void test3() throws IOException {
        Path path1 = Paths.get("D:\\workFile\\own\\hbuilder\\test1\\index.html");
        Path path2 = Paths.get("D:\\index2.html");
        Files.copy(path1,path2, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void test2(){
        //Path 中基本信息的使用
        Path path = Paths.get("D:\\", "workFile\\own\\hbuilder\\test1\\index.html");
        System.out.println("getFileName=="+path.getFileName());
        System.out.println("getFileSystem=="+path.getFileSystem());
        System.out.println("endsWith=="+path.endsWith("index.html"));
        System.out.println("startsWith=="+path.startsWith("e:\\"));
        System.out.println("isAbsolute=="+path.isAbsolute());
        System.out.println(path.getNameCount());
        for (int i=0;i<path.getNameCount();i++){
            System.out.println("file=="+path.getName(i));
        }
    }

    @Test
    public void test1(){
        Path path = Paths.get("D:\\workFile\\own\\hbuilder\\test1\\index.html");
        //获取父目录
        System.out.println(path.getParent());
        //获取根目录
        System.out.println(path.getRoot());

        //将指定的路径解析为其他的路径
        Path path2 = path.resolve("d:\\index.html");
        System.out.println("path2 ="+path2.toString());

        //设置index.html 的相对路径
        Path path1 = Paths.get("index.html");
        //获取指定文件的绝对路径
        Path newPath = path1.toAbsolutePath();
        System.out.println("newpath=="+newPath);

        System.out.println(path.toString());
    }


}
