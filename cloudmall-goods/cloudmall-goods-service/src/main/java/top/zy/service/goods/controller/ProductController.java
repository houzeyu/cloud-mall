package top.zy.service.goods.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import top.zy.common.util.utils.req.BasePageReq;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.common.util.vo.PageResult;
import top.zy.service.goods.entity.Product;
import top.zy.service.goods.entity.ProductDetail;
import top.zy.service.goods.entity.ProductSpecification;
import top.zy.service.goods.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    /***
     * 分页查询spu
     * @param basePageReq
     * @return
     */
    @GetMapping("page")
    public ApiResponse selectProductPage(BasePageReq basePageReq){
          List<Product> products=productService.selectProductPage(basePageReq);
           PageResult<Product> pageResult=new PageResult<>();
           pageResult.setItems(products);
           pageResult.setTotal((long) products.size());
          return ResponseUtil.ok(pageResult);
    }

    /****
     * 通过spuId查询spu详情
     * @param productId
     * @return
     */
    @GetMapping("detail/{productId}")
    public ApiResponse selectProductDetail(@PathVariable("productId") Long productId){
        ProductDetail productDetail=productService.selectProductDetail(productId) ;
        return ResponseUtil.ok(productDetail);
    }

    /****
     * 通过spuId查询sku
     * @param productId
     * @return
     */
    @GetMapping("specification/{productId}")
    public ApiResponse selectProductSpecification(@PathVariable("productId")Long productId){
          List<ProductSpecification> productSpecifications=productService.selectProductSpecification(productId);
          return ResponseUtil.ok(productSpecifications);
    }
}
