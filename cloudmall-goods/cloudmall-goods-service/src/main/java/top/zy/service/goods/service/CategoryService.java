package top.zy.service.goods.service;

import top.zy.service.goods.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> selectCategorysByIds(List<Long> ids);
}
