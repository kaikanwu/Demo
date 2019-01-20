package websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author kaikanwu
 * @date 20/01/2019
 */
public class WSServer {


    public static void main(String[] args) throws Exception{

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServerInitializer());

            ChannelFuture future = serverBootstrap.bind(8088).sync();

            future.channel().closeFuture().sync();

        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }

}
