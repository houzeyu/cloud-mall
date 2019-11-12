package top.zy.gateway.config.dynamic;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.util.RequestUtils;

import org.springframework.util.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import java.util.*;


@Slf4j
public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Value("${gateway.group}")
    private String gatewayGroup;
    @Autowired
    private GatewayApiMapper gatewayApiMapper;

    private ZuulProperties properties;
    private String dispatcherServletPath = "/";
    private String zuulServletPath;

     private List<ZuulRouteVO> zuulRouteVOList=new ArrayList<>();

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
        List<ZuulApiGroupDto> gatewayApiGroupList=gatewayApiMapper.selectApiGroupList(gatewayGroup);
         List<ZuulRouteVO> results = gatewayApiMapper.selectApiList(gatewayApiGroupList);
//        this.properties.setPrefix(gatewayApiGroupList.get(0).getGatewayApiGroupPath());
             results.stream().forEach(api->{
                 gatewayApiGroupList.stream().forEach(apiGroupDto -> {
                     if (api.getGatewayApiGroupId()==apiGroupDto.getGatewayApiGroupId()){
                         api.setApiPath(apiGroupDto.getGatewayApiGroupPath());
                         ZuulRouteVO zuulRouteVO=new ZuulRouteVO();
                         BeanUtils.copyProperties(api,zuulRouteVO);
                         zuulRouteVOList.add(zuulRouteVO);
                         if (StringUtils.hasText(api.getApiPath())) {
                             api.setPath(api.getApiPath() + api.getPath());
                             if (!api.getPath().startsWith("/")) {
                                 api.setPath("/" + api.getPath());
                             }
                         }
                     }
             });
        });
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
                zuulRoute.setSensitiveHeaders(result.getSensitiveHeaders());
                zuulRoute.setCustomSensitiveHeaders(result.isCustomSensitiveHeaders());
            } catch (Exception e) {
                log.error("=============加载网关路由失败==============", e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    @Override
    public Route getMatchingRoute(String path) {
        if (log.isDebugEnabled()) {
            log.debug("Finding route for path: " + path);
        }
        this.getRoutesMap();
        if (log.isDebugEnabled()) {
            log.debug("servletPath=" + this.dispatcherServletPath);
            log.debug("zuulServletPath=" + this.zuulServletPath);
            log.debug("RequestUtils.isDispatcherServletRequest()=" + RequestUtils.isDispatcherServletRequest());
            log.debug("RequestUtils.isZuulServletRequest()=" + RequestUtils.isZuulServletRequest());
        }
        //2.对url路径预处理
        String adjustedPath = adjustPath(path);

        //3.根据路径获取匹配的ZuulRoute
        ZuulRoute route = getZuulRoute(adjustedPath);
        Route routeResult = this.getRoute(route, adjustedPath);
        return routeResult;
    }
    private String adjustPath(final String path) {
        String adjustedPath = path;
        if (RequestUtils.isDispatcherServletRequest() && StringUtils.hasText(this.dispatcherServletPath)) {
            if (!this.dispatcherServletPath.equals("/") && path.startsWith(this.dispatcherServletPath)) {
                adjustedPath = path.substring(this.dispatcherServletPath.length());
                log.debug("Stripped dispatcherServletPath");
            }
        } else if (RequestUtils.isZuulServletRequest() && StringUtils.hasText(this.zuulServletPath) && !this.zuulServletPath.equals("/")) {
            adjustedPath = path.substring(this.zuulServletPath.length());
            log.debug("Stripped zuulServletPath");
        }

        log.debug("adjustedPath=" + adjustedPath);
        return adjustedPath;
    }
    protected Route getRoute(ZuulRoute route, String path) {
        if (route == null) {
            return null;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("route matched=" + route);
            }

            String targetPath = path;
            String prefix =null;
            ZuulRouteVO zuulRouteVO=new ZuulRouteVO();
            zuulRouteVOList.stream().forEach(api->{
                if (route.getId().equals(api.getId())){
                    zuulRouteVO.setApiPath(api.getApiPath());
                }
            });
            prefix=zuulRouteVO.getApiPath();
            if (prefix.endsWith("/")) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }

            if (path.startsWith(prefix + "/") && this.properties.isStripPrefix()) {
                targetPath = path.substring(prefix.length());
            }

            if (route.isStripPrefix()) {
                int index = route.getPath().indexOf("*") - 1;
                if (index > 0) {
                    String routePrefix = route.getPath().substring(0, index);
                    targetPath = targetPath.replaceFirst(routePrefix, "");
                    prefix = prefix + routePrefix;
                }
            }

            Boolean retryable = this.properties.getRetryable();
            if (route.getRetryable() != null) {
                retryable = route.getRetryable();
            }

              Route  routeResult=new Route(route.getId(), targetPath, route.getLocation(), prefix, retryable, route.isCustomSensitiveHeaders() ? route.getSensitiveHeaders() : null, route.isStripPrefix());
               return routeResult;
        }
    }
}
