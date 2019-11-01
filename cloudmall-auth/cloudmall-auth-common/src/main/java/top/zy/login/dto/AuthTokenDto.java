package top.zy.login.dto;

import lombok.Data;

@Data
public class AuthTokenDto {

    private String token;

    private int  expireTime;

    private String refreshToken;

    private int refreshExpireTime;
}
