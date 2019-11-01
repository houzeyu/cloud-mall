package top.zy.login.utils;

import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import top.zy.common.util.utils.security.Base64Util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;
@Slf4j
public class JwtUtil {
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 从token中获取Jws<Claims>
     *
     * @param token token
     * @return Jws<Claims>
     */
    public static Jws<Claims> getClaimsFromToken(String token,byte[] publicKey) {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey generatePublic = factory.generatePublic(spec);
            return Jwts.parser()
                    .setSigningKey(generatePublic)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            log.error("token解析错误", e);
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public static Date getExpirationDateFromToken(String token,byte[] publicKey) {
        return getClaimsFromToken(token,publicKey).getBody().getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private static Boolean isTokenExpired(String token,byte[] publicKey) {
        Date expiration = getExpirationDateFromToken(token,publicKey);
        return expiration.before(new Date());
    }

    /**
     * 为指定用户生成token
     *@param privateKey 私钥字节数组
     * @param claims 用户信息
     * @return token
     */
    public static String generateToken(String privateKey,Map<String, Object> claims,int expireMinutes) {
        Date createdTime = new Date();
        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey generatePrivate =factory.generatePrivate(spec);
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(createdTime)
                    .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                    .signWith(generatePrivate, SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
           log.error("create token fail ",e);
        }
        return null;
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public static  Boolean validateToken(String token,String publicKey) {
        return !isTokenExpired(token,Base64Util.decode(publicKey));
    }
}
