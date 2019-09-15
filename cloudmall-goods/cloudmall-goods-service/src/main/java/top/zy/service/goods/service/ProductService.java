package top.zy.service.goods.service;

import top.zy.common.util.utils.req.BasePageReq;
import top.zy.service.goods.entity.Product;
import top.zy.service.goods.entity.ProductDetail;
import top.zy.service.goods.entity.ProductSpecification;

import java.util.List;

public interface ProductService {
    List<Product> selectProductPage(BasePageReq basePageReq);

    ProductDetail selectProductDetail(Long productId);

    List<ProductSpecification> selectProductSpecification(Long productId);
}
