package com.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *  初始化器，当 channel 注册后，会执行里面的相应的初始化方法
 * @author kaikanwu
 * @date 20/01/2019
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        // 通过 socketChannel 去获取对应的管道
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        // 通过管道，添加 handler(可以理解为拦截器)
        // HttpServerCodec 是由 netty 提供的 handler
        // 当请求到服务端时，我们需要做解码，响应到客户端时做编码
        channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加一个自定义的 handler， 返回 "hello netty" 字符串
        channelPipeline.addLast("customHandler", new CustomHandler());
    }
}