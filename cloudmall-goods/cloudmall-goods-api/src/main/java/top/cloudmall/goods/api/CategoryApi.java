package top.cloudmall.goods.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.zy.common.util.utils.resp.ApiResponse;

import java.util.List;

public interface CategoryApi {
    /****
     * 根据ids查询分类集合
     * @param ids
     * @return
     */
    @GetMapping("ids")
    ApiResponse selectCategoryLit(@RequestParam List<Long> ids);
}
