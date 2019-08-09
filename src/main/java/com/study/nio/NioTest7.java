package com.study.nio;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2019/8/8.
 *
 */
public class NioTest7 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        System.out.println(buffer);

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer);

    }
}
