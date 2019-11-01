package top.zy.service.goods.service;

import top.zy.service.goods.entity.SpecParam;

import java.util.List;

public interface SpecService {
    List<SpecParam> selectSpecParamsBycategoryId(Long categoryId);
}
