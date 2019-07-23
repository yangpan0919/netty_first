package com.study.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws Exception{
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
/**
 * TBinaryProtocol  二进制格式
 * TCompactProtocol  压缩格式
 */
        arg.protocolFactory(new TCompactProtocol.Factory());  //与客户端统一
        arg.transportFactory(new TFramedTransport.Factory()); //与客户端统一

        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg); //半同步半异步的

        System.out.println("Thrift Server Started!");

        server.serve();//非阻塞异步的死循环
    }
}
