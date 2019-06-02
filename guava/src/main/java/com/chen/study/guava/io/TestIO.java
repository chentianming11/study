package com.chen.study.guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.*;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.List;


/**
 * Created by chen on 2018/2/27.
 */
public class TestIO {

    public static String NEW_LINE = System.getProperty("line.separator");

    /**
     * 测试ByteStreams工具类
     */
    @Test
    public void test() throws IOException {

        File file = new File("F://aaa.txt");
        Files.touch(file);

        FileInputStream inputStream = new FileInputStream(new File("F://input.txt"));
        FileOutputStream outputStream = new FileOutputStream(new File("F://output.txt"));
        // 将 输入流转换成字节数组
        byte[] bytes = ByteStreams.toByteArray(inputStream);
        // 拷贝：将输入流的内容写入到输出流
        ByteStreams.copy(inputStream, outputStream);
        byte[] b1 = new byte[1024];
        // 全部读取：输入流的内容全部读取到一个byte数组
        ByteStreams.readFully(inputStream, b1);
        int n = 10;
        // 跳过n个字节
        ByteStreams.skipFully(inputStream, n);
        // 获取空的输出流
        OutputStream nullOutputStream = ByteStreams.nullOutputStream();

        inputStream.close();
        outputStream.close();

    }

    /**
     * 测试Files工具类
     */
    @Test
    public void test2() throws IOException {
        File file = new File("F://input.txt");
        File file2 = new File("F://output.txt");

        // 通过file创建字节源
        ByteSource byteSource = Files.asByteSource(file);
        // 通过file创建字节汇
        ByteSink byteSink = Files.asByteSink(file, FileWriteMode.APPEND);
        // 按行读取file文件到List<String>集合
        List<String> list = Files.readLines(file, Charsets.UTF_8);
        // 将字节数组内容写入到一个file中
        Files.write("aaa".getBytes(), file);
        // 复制文件 file -> file2
        Files.copy(file, file2);
        // 复制：file -> outputStream
        Files.copy(file, new FileOutputStream(file2));
        // 创建临时目录
        File tempDir = Files.createTempDir();
        // 判断文件是否相等
        boolean equal = Files.equal(file, file2);
        // 获取文件扩展名
        String fileExtension = Files.getFileExtension("xxx.jpg");
        // 获取不带扩展名的文件名
        String nameWithoutExtension = Files.getNameWithoutExtension("xxx.jpg");
        // 获取file的bufferReader
        BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8);
        // 移动
        Files.move(file, file2);
        // 创建一个新的file或者更新时间戳
        Files.touch(file);

    }

    /**
     * 测试字节源
     */
    @Test
    public void test3() throws IOException {

        File file = new File("F://input.txt");
        File file2 = new File("F://output.txt");
        // 创建源
        ByteSource byteSource = Files.asByteSource(file);
        URL url = new URL("http://www.baidu.com");
        ByteSource byteSource1 = Resources.asByteSource(url);
        // 创建汇
        ByteSink byteSink = Files.asByteSink(file2, FileWriteMode.APPEND);

        // 字节源转换成字符源
        CharSource charSource = byteSource.asCharSource(Charsets.UTF_8);
        // 判断2个字节源的内容是否相等
        boolean b = byteSource.contentEquals(byteSource);
        // 字节源内容拷贝到字节汇
        byteSource.copyTo(byteSink);
        // 获取字节源对应的输入流
        InputStream inputStream = byteSource.openBufferedStream();
        // 读取字节源内容到一个字节数组
        byte[] read = byteSource.read();
        // 获取字节源的大小
        long size = byteSource.size();
        // 截取字节源
        ByteSource slice = byteSource.slice(0, 100);
    }

    /**
     * 测试字节汇
     */
    public void test4() throws IOException {
        File file = new File("F://input.txt");
        ByteSink byteSink = Files.asByteSink(file, FileWriteMode.APPEND);
        // 字节汇转成字符汇
        CharSink charSink = byteSink.asCharSink(Charsets.UTF_8);
        // 获取字节汇的对应的输出流
        OutputStream outputStream = byteSink.openStream();
        // 向字节汇中写入内容
        byteSink.write("ss".getBytes());
        // 从输入流中将内容写入到字节汇
//        byteSink.writeFrom(inputStream)
    }
}
