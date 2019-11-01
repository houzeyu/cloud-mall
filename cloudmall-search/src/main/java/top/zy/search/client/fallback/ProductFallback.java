package top.zy.search.client.fallback;

import org.springframework.stereotype.Component;
import top.zy.common.util.utils.req.BasePageReq;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.search.client.ProductClient;
@Component
public class ProductFallback implements ProductClient {
    @Override
    public ApiResponse selectProductPage(BasePageReq basePageReq) {
        return null;
    }

    @Override
    public ApiResponse selectProductDetail(Long productId) {
        return null;
    }

    @Override
    public ApiResponse selectProductSpecification(Long productId) {
        return null;
    }
}
