package press.whcj.vichat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import press.whcj.vichat.config.YmlProperties;

/**
 * @author xyyxhcj@qq.com
 * @since 2020/04/21
 */
@SpringBootApplication
@EnableConfigurationProperties({YmlProperties.class})
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
