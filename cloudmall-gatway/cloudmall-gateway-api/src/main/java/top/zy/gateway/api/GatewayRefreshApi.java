package top.zy.gateway.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *@Author gnn [www.coder520.com]
 *@Date 2019/10/13 16:06
 *@Descrintion 网关刷新
 */
public interface GatewayRefreshApi {
    /**
     *@Author gnn [www.coder520.com]
     *@Date 2019/10/4 13:34
     *@Descrintion  刷新映射
     */
    @GetMapping("/gateway/refreshApiRoute")
    String refresh() ;
    /**
     *@Author gnn [www.coder520.com]
     *@Date 2019/10/4 13:34
     *@Descrintion  查看映射关系
     */
    @RequestMapping("/gateway//watchApiRoute")
    Object watchNowRoute();
}
