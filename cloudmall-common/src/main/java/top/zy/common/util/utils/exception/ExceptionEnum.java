package top.zy.common.util.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 17:21 2019/7/10
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionEnum {

    INVALID_USER_DATA_TYPE("参数错误",400),
    UPDATE_FAIL("更新失败",507),
    INVALID_EMPTY("参数为空",408),
    AUTH_FAIL("授权失败",4011);
    private String message;
    private int code;

}
