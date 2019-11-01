package top.zy.service.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zy.service.goods.entity.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
