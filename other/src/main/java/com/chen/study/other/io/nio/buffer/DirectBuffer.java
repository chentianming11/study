package com.chen.study.other.io.nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区
 * Zero Copy 减少了一个拷贝的过程
 *
 * @author 陈添明
 * @date 2019/9/22
 */
public class DirectBuffer {

    public static void main(String[] args) throws Exception {
        //在Java里面存的只是缓冲区的引用地址
        //管理效率
        //首先我们从磁盘上读取刚才我们写出的文件内容
        FileInputStream fin = new FileInputStream("/Users/mac/work/git/study/other/src/main/java/com/chen/study/other/io/nio/buffer/test.txt");
        FileChannel fcin = fin.getChannel();
        //把刚刚读取的内容写入到一个新的文件中
        FileOutputStream fout = new FileOutputStream("/Users/mac/work/git/study/other/src/main/java/com/chen/study/other/io/nio/buffer/testcopy.txt");
        FileChannel fcout = fout.getChannel();
        // 使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
    }
}
