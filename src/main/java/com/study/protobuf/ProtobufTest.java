package com.study.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();
        byte[] bytes = student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);

        System.out.println(student1.getAddress());
        System.out.println(student1.getAge());
        System.out.println(student1.getName());

    }
}
