package com.study.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2019/8/8.
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);//buffer 先放再读，达到复用

        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);//写
        }

        buffer.flip();  //读写切换

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());//读
        }
        System.out.println("---------------------");
        buffer.flip();  //读写切换

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            System.out.println(i);
            buffer.put(randomNumber);//写
        }

        buffer.flip();  //读写切换

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());//读
        }
    }
}
