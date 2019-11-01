package top.cloudmall.goods.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ProductLabel {

    private Integer labelId;

    private String labelName;

    private Byte status;

    private Integer sort;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String remarks;
}