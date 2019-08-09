package com.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/8/8.
 *
 */
public class NioTest8 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while (true){
            buffer.clear();

            int read = inputChannel.read(buffer);

            System.out.println("read: " + read);

            if(-1 == read){
                break;
            }

            buffer.flip();
            outputChannel.write(buffer);
        }
        inputChannel.close();
        outputChannel.close();
    }
}
