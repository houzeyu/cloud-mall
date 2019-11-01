package top.cloudmall.goods.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductSpecification {
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