package top.zy.gateway.config.dynamic;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 12:10 2019/11/2
 */
@Data
public class ZuulApiGroupDto implements Serializable {
    private Integer gatewayApiGroupId;

    private String gatewayApiGroupPath;
}
