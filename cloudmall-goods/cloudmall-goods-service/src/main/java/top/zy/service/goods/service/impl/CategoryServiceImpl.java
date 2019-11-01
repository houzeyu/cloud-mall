package top.zy.service.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zy.service.goods.dao.CategoryMapper;
import top.zy.service.goods.entity.Category;
import top.zy.service.goods.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
     @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategorysByIds(List<Long> ids) {
        return categoryMapper.selectBatchIds(ids);
    }
}
