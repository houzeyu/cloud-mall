package top.zy.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zy.common.util.utils.req.BasePageReq;
import top.zy.service.goods.dao.*;
import top.zy.service.goods.entity.Product;
import top.zy.service.goods.entity.ProductDetail;
import top.zy.service.goods.entity.ProductSpecification;
import top.zy.service.goods.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
     @Autowired
    private ProductMapper productMapper;

     @Autowired
    private ProductDetailMapper productDetailMapper;

     @Autowired
    private ProductLabelMapper productLabelMapper;
     @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
      @Autowired
    private SpecGroupMapper specGroupMapper;

      @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<Product> selectProductPage(BasePageReq basePageReq) {
        if (basePageReq!=null){
            PageHelper.startPage(basePageReq.getPageNumber(),basePageReq.getPageSize());
        }
        List<Product> products = productMapper.selectList(null);
        return products;
    }

    @Override
    public ProductDetail selectProductDetail(Long productId) {
       return productDetailMapper.selectById(productId);
    }
    @Override
    public List<ProductSpecification> selectProductSpecification(Long productId) {
        QueryWrapper<ProductSpecification> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("spu_id",productId);
        return productSpecificationMapper.selectList(queryWrapper);
    }
}
