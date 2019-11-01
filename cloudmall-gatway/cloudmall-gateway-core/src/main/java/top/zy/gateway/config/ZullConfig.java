package top.zy.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zy.gateway.config.dynamic.ZuulRouteLocator;


@Configuration
public class ZullConfig {
    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;

    @Bean
    public ZuulRouteLocator routeLocator() {
        ZuulRouteLocator routeLocator = new ZuulRouteLocator(server.getServlet().getContextPath(), this.zuulProperties);
        return routeLocator;
    }
}
