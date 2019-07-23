package com.study.grpc;

import com.study.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();

        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(localhost); //同步的

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(localhost); //异步的

        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(myResponse.getRealname());
        System.out.println("-------------------------------------------------");

        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

        while (iterator.hasNext()) {
            StudentResponse next = iterator.next();
            System.out.println(next.getName());
            System.out.println(next.getCity());
            System.out.println(next.getAge());
        }
        System.out.println("-------------------------------------------------");
        //只要客户端以流式的向服务端发送消息，一定是异步的
        StreamObserver<StudentResponseList> streamObserver = new StreamObserver<StudentResponseList>() {

            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(x -> {
                    System.out.println(x.getName());
                    System.out.println(x.getAge());
                    System.out.println(x.getCity());
                    System.out.println("***************************");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };
        StreamObserver<StudentRequest> studentsWrapperByAges = stub.getStudentsWrapperByAges(streamObserver);

        studentsWrapperByAges.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentsWrapperByAges.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentsWrapperByAges.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentsWrapperByAges.onNext(StudentRequest.newBuilder().setAge(50).build());

        studentsWrapperByAges.onCompleted();

        System.out.println("-------------------------------------------------");

        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

        for (int i = 0; i < 10; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }


        try {
            Thread.sleep(50000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }











    }
}
