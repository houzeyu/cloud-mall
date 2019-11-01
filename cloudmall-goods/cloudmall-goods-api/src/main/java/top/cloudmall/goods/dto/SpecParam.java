package top.cloudmall.goods.dto;

import lombok.Data;

import java.util.Date;

@Data

public class SpecParam {

    private Long id;

    private Long cid;

    private Long groupId;

    private String name;

    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searching;

    private String segments;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}