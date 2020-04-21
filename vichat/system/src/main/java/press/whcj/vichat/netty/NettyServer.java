package press.whcj.vichat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xyyxhcj@qq.com
 * @since 2020/04/21
 */

@Slf4j
@Component
public class NettyServer {
    private final EventLoopGroup mainGroup = new NioEventLoopGroup();
    private final EventLoopGroup subGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(int port) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(getChildHandler());
            channelFuture = serverBootstrap.bind(port).sync();
            channel = channelFuture.channel();
            log.info("======Netty Server Start=========");
        } catch (Exception e) {
            log.error("Netty server error", e);
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                log.info("Netty server listening on port {}", port);
            } else {
                log.error("Netty server start Error!");
            }
        }
        return channelFuture;
    }

    private ChannelInitializer<SocketChannel> getChildHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new ChunkedWriteHandler());
                pipeline.addLast(new HttpObjectAggregator(1024 * 64));
                pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                pipeline.addLast(new NettyServerHandler());
            }
        };
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Server...");
        if (channel != null) {
            channel.close();
        }
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }

}
