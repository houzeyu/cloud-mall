package top.zy.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cloudmall.goods.dto.Product;
import top.zy.search.dto.GoodsDto;
import top.zy.search.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {
     @Autowired
     @Qualifier("searchServiceImpl")
     private SearchService searchService;

     /****
      * 根据商品信息将数据导入es
      * @param products
      */
     @PostMapping(value = "toEs")
     private void databulkToEs(List<Product> products){
      searchService.productDataToEs(products);
     }
}
