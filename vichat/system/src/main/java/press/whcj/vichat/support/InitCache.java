package press.whcj.vichat.support;

import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import press.whcj.vichat.config.YmlProperties;
import press.whcj.vichat.netty.NettyServer;

import javax.annotation.Resource;

/**
 * system init
 *
 * @author xyyxhcj@qq.com
 * @since 2019/12/31
 */
@Component
@Slf4j
public class InitCache implements CommandLineRunner {
    @Resource
    private YmlProperties ymlProperties;
    @Resource
    private NettyServer nettyServer;

    @Override
    public void run(String... args) {
        System.out.println(">>>>>>>>>>>>>>> system init start <<<<<<<<<<<<<");
        System.out.println(">>>>>>>>>>>>>>> system init end <<<<<<<<<<<<<");
        ChannelFuture future = nettyServer.start(ymlProperties.getNettyPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        future.channel().closeFuture().syncUninterruptibly();
    }
}
