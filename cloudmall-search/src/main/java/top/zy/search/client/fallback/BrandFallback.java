package top.zy.search.client.fallback;

import org.springframework.stereotype.Component;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.search.client.BrandClient;
@Component
public class BrandFallback implements BrandClient {
    @Override
    public ApiResponse selectBrand(Long brandId) {
        return null;
    }
}
