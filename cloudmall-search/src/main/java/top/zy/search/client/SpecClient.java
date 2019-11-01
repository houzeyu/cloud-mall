package top.zy.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.cloudmall.goods.api.SpecApi;
import top.zy.search.client.fallback.SpecFallback;

@FeignClient(value = "product-service",fallback = SpecFallback.class)
public interface SpecClient extends SpecApi {

}
