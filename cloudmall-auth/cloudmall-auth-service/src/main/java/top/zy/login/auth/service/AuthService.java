package top.zy.login.auth.service;

import top.zy.login.dto.AuthLoginReqDto;
import top.zy.login.dto.AuthRefreshTokenReqDto;
import top.zy.login.dto.AuthTokenDto;

public interface AuthService {
    AuthTokenDto authLogin(AuthLoginReqDto authLoginReqDto);

    AuthTokenDto reFreshToken(AuthRefreshTokenReqDto refreshTokenReqDto);
}
