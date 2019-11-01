package top.zy.login.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.login.auth.service.AuthService;
import top.zy.login.dto.AuthLoginReqDto;
import top.zy.login.dto.AuthRefreshTokenReqDto;
import top.zy.login.dto.AuthTokenDto;

@RequestMapping("auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 授权登录
     * @return
     */
    @PostMapping("login")
    public ApiResponse<AuthTokenDto> authLogin(@RequestBody AuthLoginReqDto authLoginReqDto){
        AuthTokenDto authTokenDto  = authService.authLogin(authLoginReqDto);
        return ResponseUtil.ok(authTokenDto);
    }

    /**
     * 通过refreshToken获取token
     * @param refreshTokenReqDto
     * @return
     */
    @PostMapping("refreshToken")
    public ApiResponse<AuthTokenDto> reFreshToken(@RequestBody AuthRefreshTokenReqDto refreshTokenReqDto){
        AuthTokenDto authTokenDto  = authService.reFreshToken(refreshTokenReqDto);
        return ResponseUtil.ok(authTokenDto);
    }
}
