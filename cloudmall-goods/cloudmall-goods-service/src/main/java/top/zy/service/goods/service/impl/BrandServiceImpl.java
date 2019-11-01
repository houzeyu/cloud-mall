package top.zy.service.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zy.service.goods.dao.BrandMapper;
import top.zy.service.goods.entity.Brand;
import top.zy.service.goods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public Brand selectBrandById(Long brandId) {
        return brandMapper.selectById(brandId);
    }
}
