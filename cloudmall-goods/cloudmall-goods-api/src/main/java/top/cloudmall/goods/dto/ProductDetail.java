package top.cloudmall.goods.dto;


import lombok.Data;

import java.util.Date;

@Data

public class ProductDetail {

    private Long productId;

    private String genericSpec;

    private String specialSpec;

    private String packingList;

    private String afterService;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String description;
}