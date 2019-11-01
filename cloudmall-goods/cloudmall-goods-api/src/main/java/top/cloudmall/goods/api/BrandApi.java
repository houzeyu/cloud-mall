package top.cloudmall.goods.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zy.common.util.utils.resp.ApiResponse;

public interface BrandApi {
    /*****
     * 根据品牌id查询品牌
     * @param brandId
     * @return
     */
     @GetMapping("{brandId}")
     ApiResponse selectBrand(@PathVariable("brandId") Long brandId);
}
