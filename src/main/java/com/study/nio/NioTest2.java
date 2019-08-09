package com.study.nio;

import java.io.FileInputStream;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/8/8.
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer.mark());
        byteBuffer.flip();

        while (byteBuffer.remaining()>0){
            System.out.println(byteBuffer.mark());
            System.out.println("----------------");
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char) b);

        }
        fileInputStream.close();
    }
}
