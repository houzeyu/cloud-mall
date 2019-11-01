package top.zy.gateway.config.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Value("${gateway.group}")
    private String gatewayGroup;
    @Autowired
    private GatewayApiMapper gatewayApiMapper;

    private ZuulProperties properties;

        public ZuulRouteLocator(String servletPath, ZuulProperties properties) {
            super(servletPath, properties);
            this.properties = properties;
            log.info("servletPath:{}", servletPath);
        }
    @Override
    public void refresh() {
        doRefresh();
    }
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从DB中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        log.info("网关路由配置 --> {}", routesMap);
        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        log.info("gatewayGroup:{}",gatewayGroup);
        List<ZuulRouteVO> results = gatewayApiMapper.selectApiList(gatewayGroup);
        if(results==null){
            return routes;
        }
        for (ZuulRouteVO result : results) {
            if (StringUtils.isEmpty(result.getPath())) {
                continue;
            }
            if (!result.getEnabled()) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
                log.error("=============加载网关路由失败==============", e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }
}
