package top.zy.search.client.fallback;

import org.springframework.stereotype.Component;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.search.client.CategoryClient;

import java.util.List;
@Component
public class CategoryFallback implements CategoryClient {
    @Override
    public ApiResponse selectCategoryLit(List<Long> ids) {
        return null;
    }
}
