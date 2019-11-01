package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_brand")
public class Brand {
    @TableId
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