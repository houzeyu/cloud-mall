package top.zy.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.cloudmall.goods.api.BrandApi;
import top.zy.search.client.fallback.BrandFallback;
import top.zy.search.client.fallback.SpecFallback;

@FeignClient(value = "product-service",fallback = BrandFallback.class)
public interface BrandClient  extends BrandApi  {

}
