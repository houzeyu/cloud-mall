package top.zy.user.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.login.dto.AuthLoginReqDto;
import top.zy.user.dto.UserInfoDto;

public interface UserApi {
    @PostMapping("/user/login")
    ApiResponse<UserInfoDto> login(@RequestBody AuthLoginReqDto authLoginReqDto);
}
