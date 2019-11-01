package top.zy.search.client.fallback;

import org.springframework.stereotype.Component;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.search.client.SpecClient;
@Component
public class SpecFallback implements SpecClient {
    @Override
    public ApiResponse selectSpec(Long categoryId) {
        return null;
    }
}
