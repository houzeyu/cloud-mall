package top.zy.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.gateway.config.dynamic.RefreshRouteService;
import top.zy.gateway.config.dynamic.ZuulRouteLocator;

import javax.annotation.PostConstruct;
import java.util.Map;
@RequestMapping("gateway")
@RestController
public class GatewayRefreshController {

    @Autowired
    RefreshRouteService refreshRouteService;
    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;


   /**
    *@Author gnn [www.coder520.com]
    *@Date 2019/10/4 13:34
    *@Descrintion  刷新映射
    */
    @GetMapping("/refreshApiRoute")
    public ApiResponse refresh() {
        refreshRouteService.refreshRoute();
        return ResponseUtil.gwRoutesRefreshOk();
    }
}
