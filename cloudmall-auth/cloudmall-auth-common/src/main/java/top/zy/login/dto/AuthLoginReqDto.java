package top.zy.login.dto;

import lombok.Data;

/**
 * 授权登录请求DTO
 */
@Data
public class AuthLoginReqDto {
    private String username;
    private String password;
}
