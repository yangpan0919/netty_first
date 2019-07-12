package com.study.fourexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加一个空闲状态的处理器
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));

        pipeline.addLast(new MyServerHandler());

    }
}
