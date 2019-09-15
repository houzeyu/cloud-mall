package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_product_detail")
public class ProductDetail {
    @TableId
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