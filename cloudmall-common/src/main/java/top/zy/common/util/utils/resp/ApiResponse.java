package top.zy.common.util.utils.resp;

import lombok.Data;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 17:23 2019/7/12
 */
@Data
public class ApiResponse<T> {
    private int errno;
    private String errmsg;
    private T data;


    public ApiResponse(int errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public ApiResponse(ApiResponseStatus success) {
        this.errno=success.getErrno();
        this.errmsg=success.getErrmsg();
    }
}
