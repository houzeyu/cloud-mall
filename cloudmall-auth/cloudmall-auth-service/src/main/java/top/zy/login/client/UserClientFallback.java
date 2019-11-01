package top.zy.login.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ApiResponseStatus;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.login.dto.AuthLoginReqDto;
import top.zy.user.dto.UserInfoDto;
@Component
@Slf4j
public class UserClientFallback implements UserClient {

    @Override
    public ApiResponse<UserInfoDto> login(AuthLoginReqDto authLoginReqDto) {
        log.warn("user-service断路器打开");
        return ResponseUtil.fail(ApiResponseStatus.INTERNAL_ERROR);
    }
}
