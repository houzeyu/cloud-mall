package top.zy.gateway.config.dynamic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GatewayApiMapper extends BaseMapper<ZuulRouteVO> {
  List<ZuulRouteVO> selectApiList(@Param("gatewayApiGroupList") List<ZuulApiGroupDto>  gatewayApiGroupList);

  List<ZuulApiGroupDto> selectApiGroupList(@Param("gatewayGroupName") String gatewayGroup);
}
