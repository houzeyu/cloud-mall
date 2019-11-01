package top.zy.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zy.service.goods.dao.SpecGroupMapper;
import top.zy.service.goods.dao.SpecParamMapper;
import top.zy.service.goods.entity.SpecParam;
import top.zy.service.goods.service.SpecService;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Override
    public List<SpecParam> selectSpecParamsBycategoryId(Long categoryId) {
        QueryWrapper<SpecParam> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cid",categoryId);
        return specParamMapper.selectList(queryWrapper);
    }
}
