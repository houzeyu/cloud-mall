package top.zy.service.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("os_spec_group")
public class SpecGroup {
    @TableId
    private Long id;

    private Long cid;

    private String name;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}