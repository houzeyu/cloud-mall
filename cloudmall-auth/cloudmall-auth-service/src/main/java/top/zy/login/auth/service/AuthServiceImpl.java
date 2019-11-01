package top.zy.login.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.zy.common.util.utils.exception.CloudmalException;
import top.zy.common.util.utils.exception.ExceptionEnum;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ApiResponseStatus;
import top.zy.login.client.UserClient;
import top.zy.login.config.JwtProperties;
import top.zy.login.dto.AuthLoginReqDto;
import top.zy.login.dto.AuthRefreshTokenReqDto;
import top.zy.login.dto.AuthTokenDto;
import top.zy.login.util.JwtConstants;
import top.zy.login.utils.JwtUtil;
import top.zy.user.dto.UserInfoDto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public AuthTokenDto authLogin(AuthLoginReqDto authLoginReqDto) {
        //调用用户服务验证用户名密码
        ApiResponse<UserInfoDto> loginResult = userClient.login(authLoginReqDto);
        AuthTokenDto authTokenDto=new AuthTokenDto();
        if (loginResult.getErrno()==0){
             UserInfoDto userInfoDto = loginResult.getData();
             Map<String,Object> claims=new HashMap<>();
             claims.put("userInfo",userInfoDto.getUserId());
             //登录成功生成token
             String token = JwtUtil.generateToken(jwtProperties.getPrivateKey(), claims, jwtProperties.getExpireTime());
             authTokenDto.setToken(JwtConstants.TOKEN_PREFIX+token);
             authTokenDto.setExpireTime(jwtProperties.getExpireTime());
             String reFreshToken=JwtUtil.generateToken(jwtProperties.getPrivateKey(), claims, jwtProperties.getReFreshExpireTime());
             authTokenDto.setRefreshExpireTime(jwtProperties.getReFreshExpireTime());
             authTokenDto.setRefreshToken(reFreshToken);
             //保存token到redis
            redisTemplate.opsForValue().set(JwtConstants.TOKEN_KEY_PREFIX+token,JSON.toJSONString(userInfoDto),jwtProperties.getExpireTime(),TimeUnit.SECONDS);
            //保存refreshToken到redis
            redisTemplate.opsForValue().set(JwtConstants.REFRESHTOKEN_KEY_PREFIX+reFreshToken,JSON.toJSONString(userInfoDto),jwtProperties.getReFreshExpireTime(),TimeUnit.SECONDS);
        }else {
            throw new CloudmalException(loginResult.getErrmsg(),loginResult.getErrno());
        }
        return authTokenDto;
    }

    @Override
    public AuthTokenDto reFreshToken(AuthRefreshTokenReqDto refreshTokenReqDto) {
        String reFreshTokenRd = (String) redisTemplate.opsForValue().get(JwtConstants.REFRESHTOKEN_KEY_PREFIX + refreshTokenReqDto.getRefreshToken());
        AuthTokenDto authTokenDto=new AuthTokenDto();
        if (reFreshTokenRd.isEmpty()){
            throw new CloudmalException(ExceptionEnum.AUTH_FAIL);
        }else {
          // 刷新token存在， 则生成新token并删除redis中的刷新token
            String token=JwtUtil.generateToken(jwtProperties.getPrivateKey(), JSONObject.parseObject(reFreshTokenRd,Map.class), jwtProperties.getReFreshExpireTime());
            redisTemplate.opsForValue().set(JwtConstants.TOKEN_KEY_PREFIX+token,reFreshTokenRd,jwtProperties.getReFreshExpireTime(),TimeUnit.SECONDS);
             authTokenDto.setToken(token);
             authTokenDto.setExpireTime(jwtProperties.getExpireTime());
        }
        return authTokenDto;
    }
}
