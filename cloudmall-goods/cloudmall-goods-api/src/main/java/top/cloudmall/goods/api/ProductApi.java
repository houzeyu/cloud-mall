package top.cloudmall.goods.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zy.common.util.utils.req.BasePageReq;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.common.util.vo.PageResult;

import java.util.List;

public interface ProductApi {

    /***
     * 分页查询spu
     * @param basePageReq
     * @return
     */
    @GetMapping("page")
    ApiResponse selectProductPage(BasePageReq basePageReq);

    /****
     * 通过spuId查询spu详情
     * @param productId
     * @return
     */
    @GetMapping("detail/{productId}")
     ApiResponse selectProductDetail(@PathVariable("productId") Long productId);

    /****
     * 通过spuId查询sku
     * @param productId
     * @return
     */
    @GetMapping("specification/{productId}")
     ApiResponse selectProductSpecification(@PathVariable("productId")Long productId);
}
