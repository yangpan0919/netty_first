package com.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/8/8.
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileInputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);


        byte[] bytes = "hello world,nio".getBytes();

        for (int i = 0; i < bytes.length; i++) {
            byteBuffer.put(bytes[i]);
        }


        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        fileInputStream.close();
    }
}
