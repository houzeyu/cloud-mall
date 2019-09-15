package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_product_specification")
public class ProductSpecification {
    @TableId
    private Long id;

    private Long spuId;

    private String title;

    private String images;

    private Long price;

    private Integer score;

    private String indexes;

    private String ownSpec;

    private Boolean enable;

    private Date createTime;

    private Date lastUpdateTime;

    private String createBy;

    private String updateBy;
}