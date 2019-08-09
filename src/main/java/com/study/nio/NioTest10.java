package com.study.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by Administrator on 2019/8/8.
 *
 */
public class NioTest10 {

    public static void main(String[] args) throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();
        FileLock lock = channel.lock(3, 6, true); //true 共享锁， false 排它锁

        System.out.println("valid: " + lock.isValid());
        System.out.println("lock type: " + lock.isShared());

        lock.release();
        randomAccessFile.close();

    }
}
