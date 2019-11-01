package top.zy.gateway.console.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.zy.gateway.api.GatewayRefreshApi;
import top.zy.gateway.console.client.fallback.GatewayRefreshFallback;

@FeignClient(value = "",fallback = GatewayRefreshFallback.class)
public interface GatewayRefreshClient extends GatewayRefreshApi {

}
