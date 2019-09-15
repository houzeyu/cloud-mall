package top.zy.common.util.utils.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 17:21 2019/7/10
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    INVALID_USER_DATA_TYPE("参数错误",400),
    UPDATE_FAIL("更新失败",507);
    private String message;
    private int code;
}
