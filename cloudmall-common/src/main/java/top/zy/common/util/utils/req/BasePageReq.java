package top.zy.common.util.utils.req;

import lombok.Data;

@Data
public class BasePageReq {
    private int pageSize;
    private int pageNumber;
}
