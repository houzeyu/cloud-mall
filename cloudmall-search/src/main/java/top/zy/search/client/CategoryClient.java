package top.zy.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.cloudmall.goods.api.CategoryApi;
import top.zy.search.client.fallback.CategoryFallback;

@FeignClient(value = "product-service",fallback = CategoryFallback.class)
public interface CategoryClient extends CategoryApi {

}
