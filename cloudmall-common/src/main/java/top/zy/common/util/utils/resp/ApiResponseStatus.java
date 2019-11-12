package top.zy.common.util.utils.resp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 11:47 2019/7/17
 */
@NoArgsConstructor
@AllArgsConstructor
public enum  ApiResponseStatus {
    GW_ROUTES_REFRESH_SUCESS(200,"网关刷新成功"),
    SUCCESS(200,"成功"),
    BAD_ARGUMENT(401,"参数不正确"),
    INTERNAL_ERROR(503,"系统异常稍后再试"),
    BAD_GATEWAY(502,"服务器无响应"),
    UN_LOGIN(501,"请登录"),
    UN_AUTH(506,"无权限操作"),
    UPDATE_FAIL(507,"更新失败"),
    ADMIN_USERNAME_PASSWORD_FAIL(600,"账号或密码错误"),
    ADMIN_INVALID_ACCOUNT(601,"认证失败");
    private int errno;
    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
