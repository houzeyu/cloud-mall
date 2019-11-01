package top.zy.service.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.service.goods.entity.Category;
import top.zy.service.goods.service.CategoryService;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController {
    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    /****
     * 根据ids查询分类集合
     * @param ids
     * @return
     */
    @GetMapping("ids")
    public ApiResponse selectCategoryLit(@RequestParam List<Long> ids){
        List<Category> categories=categoryService.selectCategorysByIds(ids);
      return ResponseUtil.ok(categories);
    }
}
