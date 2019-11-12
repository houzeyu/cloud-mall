package top.zy.common.util.utils.resp;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 14:24 2019/7/18
 */
public class ResponseUtil {


    public static ApiResponse ok(){
      ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.SUCCESS);
      return apiResponse;
    }
    public static ApiResponse ok(Object data){
        ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.SUCCESS);
        apiResponse.setData(data);
        return apiResponse;
    }
    public static ApiResponse fail(int errno, String errmsg) {
      ApiResponse apiResponse=new ApiResponse(errno,errmsg);
        return apiResponse;
    }
    public static ApiResponse fail(ApiResponseStatus apiResponseStatus) {
        ApiResponse apiResponse=new ApiResponse(apiResponseStatus.getErrno(),apiResponseStatus.getErrmsg());
        return apiResponse;
    }
    public static ApiResponse unlogin(){
     ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.UN_LOGIN);
     return apiResponse;
    }

    public static ApiResponse unauthz() {
    ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.UN_AUTH);
    return apiResponse;
    }
    public static ApiResponse badArgument() {
        ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.BAD_ARGUMENT);
        return apiResponse;
    }
    public static ApiResponse gwRoutesRefreshOk(){
        ApiResponse apiResponse=new ApiResponse(ApiResponseStatus.GW_ROUTES_REFRESH_SUCESS);
        return apiResponse;
    }
}
