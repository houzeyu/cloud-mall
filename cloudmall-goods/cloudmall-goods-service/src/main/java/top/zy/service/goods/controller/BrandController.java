package top.zy.service.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.service.goods.entity.Brand;
import top.zy.service.goods.service.BrandService;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    @Qualifier("brandServiceImpl")
    private BrandService brandService;

    /*****
     * 根据品牌id查询品牌
     * @param brandId
     * @return
     */
    @GetMapping("{brandId}")
    public ApiResponse selectBrand(@PathVariable("brandId") Long brandId){
        Brand brand=brandService.selectBrandById(brandId);
        return ResponseUtil.ok(brand);
    }
}
