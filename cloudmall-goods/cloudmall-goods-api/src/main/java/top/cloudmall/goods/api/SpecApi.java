package top.cloudmall.goods.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.zy.common.util.utils.resp.ApiResponse;

public interface SpecApi {
    /*****
     * 通过分类id查询规格参数
     * @param categoryId
     * @return
     */
    @GetMapping("categoryId")
    ApiResponse selectSpec(@RequestParam("categoryId") Long categoryId);
}
