package top.zy.common.util.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.constants.Constants;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ApiResponseStatus;

import java.util.List;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 17:27 2019/7/12
 */
@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerAdvice {
    @ResponseBody
    @ExceptionHandler(CloudmalException.class)
    public ApiResponse handlerCloudmallException(CloudmalException e){
        log.error(e.getMessage(),e);
        return new ApiResponse(e.getStatusCode(),e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponse handlerException(Exception e){
        log.error(e.getMessage(),e);
        return new ApiResponse(ApiResponseStatus.INTERNAL_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handlerIllegalParamException(MethodArgumentNotValidException e){
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message= "参数不合法";
        if (errors.size()>0){
            message = errors.get(0).getDefaultMessage();
        }
        log.error(e.getMessage(),e);
        ApiResponse result=new ApiResponse(Constants.RESP_STATUS_BADREQUEST,e.getMessage());
        return result;
    }
}
