package top.zy.common.util.utils.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 17:27 2019/7/12
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerAdvice {


    @ResponseBody
    @ExceptionHandler(CloudmalException.class)
    public ApiResponse handlerException(CloudmalException e){
        return new ApiResponse(e.getStatusCode(),e.getMessage());
    }
}
