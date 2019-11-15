package top.zy.gateway;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 20:25 2019/10/29
 */
@Component
public class LogIpConfig extends ClassicConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogIpConfig.class);

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error("获取日志Ip异常", e);
        }
        return null;
    }
}
