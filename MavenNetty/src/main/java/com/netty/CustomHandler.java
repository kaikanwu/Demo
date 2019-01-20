package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 *  创建一个自定义的 handler，目的是返回 "hello netty"的字符串
 *
 *  SimpleChannelInboundHandler: 对于请求来说，相当于入站，入境
 * @author kaikanwu
 * @date 20/01/2019
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {


        // get the channel
        Channel channel = channelHandlerContext.channel();
        // show the client remote address

        if (httpObject instanceof HttpRequest) {
            System.out.println(channel.remoteAddress());
            // define the message need to be send, by the buffer
            ByteBuf content = Unpooled.copiedBuffer("Hello Netty Hello World!", CharsetUtil.UTF_8);

            // build a HTTP response
            FullHttpResponse response =
                    new DefaultFullHttpResponse(
                            HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK,
                            content);

            // 为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 把响应刷到（返回到）客户端
            channelHandlerContext.writeAndFlush(response);
        }


    }
}
