package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.nio.charset.StandardCharsets;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        // 1. 이벤트 루프 그룹 생성
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 클라이언트 연결 수락
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 데이터 처리

        try {
            // 2. 서버 부트스트랩 설정
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // NIO 소켓 채널 사용
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new SimpleChannelHandler());
                    }
                });

            // 3. 서버 바인딩 및 시작
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Server started on port " + port);

            // 4. 서버 종료 대기
            future.channel().closeFuture().sync();
        } finally {
            // 5. 이벤트 루프 종료
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 데이터 처리를 위한 핸들러
    static class SimpleChannelHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf byteBuf = (ByteBuf) msg;
            String message = byteBuf.toString(StandardCharsets.UTF_8);
            System.out.println("Received: " + message);
            ctx.writeAndFlush(msg); // 받은 데이터를 그대로 클라이언트에게 반환
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
