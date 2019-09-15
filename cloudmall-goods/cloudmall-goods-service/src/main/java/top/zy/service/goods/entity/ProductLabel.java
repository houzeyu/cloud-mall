package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_product_label")
public class ProductLabel {
    @TableId
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