package top.zy.common.util.utils.constants;

/**
 * @Author: HouZeYu
 * @Description:
 * @Date: Created in 21:45 2018/5/13
 */
public class Constants {
    /**  自定义状态码 **/
    public static final int RESP_STATUS_OK = 200;

    public static final int RESP_STATUS_NOAUTH = 401;

    public static final int RESP_STATUS_INTERNAL_ERROR = 500;

    public static final int RESP_STATUS_BADREQUEST = 400;
    /***自定义状态码 */

    /**用户token**/
    public static final String REQUEST_TOKEN_HEADER="x-auth-token";
    /***用户session**/
    public static final String REQUEST_USER_SESSION="current-user";
    /***用户版本**/
    public static final String REQUEST_VESSION_KEY="version";
    /**用户注册分布式锁路径**/
    public static final String USER_SMSREGISTER_DISTRIBUTE_LOCK_PATH= "/user_reg_mobile";
    public static final String USER_EMAILREGISTER_DISTRIBUTE_LOCK_PATH= "/user_reg_email";
}
