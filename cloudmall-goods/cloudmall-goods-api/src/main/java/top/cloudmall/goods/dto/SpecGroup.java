package top.cloudmall.goods.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SpecGroup {

    private Long id;

    private Long cid;

    private String name;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}