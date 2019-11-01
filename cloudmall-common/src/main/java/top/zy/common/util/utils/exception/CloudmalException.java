package top.zy.common.util.utils.exception;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 22:17 2019/6/22
 */
public class CloudmalException extends RuntimeException {
    private int statusCode;

    public CloudmalException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CloudmalException(String message) {
        super(message);
    }

    public CloudmalException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.statusCode=exceptionEnum.getCode();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
