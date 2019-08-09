package com.study.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2019/8/8.
 *
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);

        }
        buffer.position(2).limit(6);
        ByteBuffer slice = buffer.slice();//创建一个新的buffer对象，新对象的数组为老对象的子数组，共享该子数组内存，达到数据同步

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }
        buffer.position(0).limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
