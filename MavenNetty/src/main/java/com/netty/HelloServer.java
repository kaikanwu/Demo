package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  实现客户端发送一个请求，服务器会返回 "Hello Netty" 的字符串
 *
 * @author kaikanwu
 * @date 20/01/2019
 */
public class HelloServer {

    public static void main(String[] args) throws Exception{

        // 1. 构建一对 主从线程组
        // 主线程组：用于接收客户端的链接，但是不做任何处理（）
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // 从线程组：主线程组会把任务给 "从线程组"，做具体的事情
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {

            // 2. 定义服务器启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置主从线程组
            serverBootstrap.group(parentGroup, childGroup)
                    // 设置 NIO 的双向通道
                    .channel(NioServerSocketChannel.class)
                    // 自处理器，用于处理 workerGroup
                    .childHandler(new HelloServerInitializer());

            // 启动定义好的 server，并且设置 8088 为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();


            // 监听关闭的 channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        }finally {
            // shutdown 线程组
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }

}
