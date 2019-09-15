package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_spec_param")
public class SpecParam {
    @TableId
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