package top.zy.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zy.gateway.config.dynamic.RefreshRouteService;

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
    public String refresh() {
        refreshRouteService.refreshRoute();
        return "refresh success";
    }
      /**
       *@Author gnn [www.coder520.com]
       *@Date 2019/10/4 13:34
       *@Descrintion  查看映射关系
       */
    @RequestMapping("/watchApiRoute")
    public Object watchNowRoute() {
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        return handlerMap;
    }

}
