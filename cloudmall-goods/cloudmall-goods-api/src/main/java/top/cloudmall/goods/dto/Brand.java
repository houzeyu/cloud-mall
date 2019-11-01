package top.cloudmall.goods.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Brand {

    private Long brandId;

    private String name;

    private String image;

    private String letter;

    private Integer sort;

    private Byte showInHot;

    private Byte showInTop;

    private Byte showInNav;

    private Byte status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}