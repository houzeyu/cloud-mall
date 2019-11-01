package top.zy.gateway.console.client.fallback;

import top.zy.gateway.console.client.GatewayRefreshClient;

public class GatewayRefreshFallback implements GatewayRefreshClient {
    @Override
    public String refresh() {
        return null;
    }

    @Override
    public Object watchNowRoute() {
        return null;
    }
}
