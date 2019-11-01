package top.zy.search.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.zy.search.dto.GoodsDto;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 16:53 2019/8/8
 */
public interface SearchRepository extends ElasticsearchRepository<GoodsDto,Long> {

}
