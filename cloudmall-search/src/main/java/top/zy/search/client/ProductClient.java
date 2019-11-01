package top.zy.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.cloudmall.goods.api.ProductApi;
import top.zy.search.client.fallback.ProductFallback;
import top.zy.search.client.fallback.SpecFallback;

@FeignClient(value = "product-service",fallback = ProductFallback.class)
public interface ProductClient extends ProductApi {

}
