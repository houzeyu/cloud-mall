package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_category")
public class Category {
    @TableId
    private Long categoryId;

    private String name;

    private Long parentId;

    private Boolean isParent;

    private Integer sort;

    private Byte status;

    private Byte showInNav;

    private Byte showInTop;

    private Byte showInHot;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String pageTitle;

    private String pageDescription;

    private String pageKeyword;

    private String remarks;
}