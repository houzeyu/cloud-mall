package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_product")
public class Product {
    @TableId
    private Long id;

    private String title;

    private String subTitle;

    private Long cid1;

    private Long cid2;

    private Long cid3;

    private Long brandId;

    private Boolean saleable;

    private Boolean valid;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer sellerId;

    private Integer labelId;

    private Byte showInTop;

    private Byte showInNav;

    private Byte showInHot;

    private String createBy;

    private Date shelveTime;

    private String shelveBy;

    private String updateBy;

    private String remarks;

}