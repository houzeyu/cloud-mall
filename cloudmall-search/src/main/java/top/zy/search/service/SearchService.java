package top.zy.search.service;

import top.cloudmall.goods.dto.Product;

import java.util.List;

public interface SearchService {
    void productDataToEs(List<Product> productIds);

}
