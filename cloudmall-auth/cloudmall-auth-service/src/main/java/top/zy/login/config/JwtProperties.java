package top.zy.login.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import top.zy.common.util.utils.security.RSAUtil;
import top.zy.login.util.JwtConstants;
import top.zy.login.utils.JwtUtil;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Data
@ConfigurationProperties(prefix = "cloudmall.jwt")
@Slf4j
public class JwtProperties {


    private String pubKeyPath;// 公钥地址

    private String priKeyPath;// 私钥地址

    private int expireTime;// token过期时间

    private String reFreshToken; //刷新token

    private int reFreshExpireTime;// 刷新token过期时间

    private String privateKey; // 私钥

    private String publicKey; // 公钥

    @PostConstruct
    private void init(){
        try {
             privateKey = RSAUtil.readPrivateKey(priKeyPath);
             publicKey = RSAUtil.readPublicKey(pubKeyPath);
//            log.info("privateKey: {}",privateKey);
//            log.info("publicKey: {}",publicKey);
            log.info("privateKey and publicKey  init success");
//            Map<String,Object> claims=new HashMap<>();
//            claims.put("id","123455");
//            String token = JwtUtil.generateToken(privateKey, claims, expireTime);
//            boolean result=JwtUtil.validateToken(token,publicKey);
//            String reFreshToken=JwtUtil.generateToken(privateKey, claims, reFreshExpireTime);
//            log.info("token: {}", JwtConstants.TOKEN_PREFIX+token);
//            log.info("reFreshToken: {}", JwtConstants.TOKEN_PREFIX+reFreshToken);
//            log.info("tokenValidate : {}", result);
//            //保存token到redis
//            redisTemplate.opsForValue().set(JwtConstants.TOKEN_KEY_PREFIX+token,JSON.toJSONString(claims),expireTime,TimeUnit.SECONDS);
//            //保存refreshToken到redis
//            redisTemplate.opsForValue().set(JwtConstants.REFRESHTOKEN_KEY_PREFIX+reFreshToken,JSON.toJSONString(claims),reFreshExpireTime,TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("init privateKey and publicKey fail",e);
        }
    }
}
