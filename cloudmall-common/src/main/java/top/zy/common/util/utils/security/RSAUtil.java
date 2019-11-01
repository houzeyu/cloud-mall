package top.zy.common.util.utils.security;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    private static final String KEY_ALGORITHM = "RSA";

    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCreOFz9nbCTY2a/iAyoIs2 y16bgpUZn/XwxapRXPa8xwc1NqtF6tXa9ahzt7Qo1keZaxbJCIf31xHLZaJM UYva7DIBcoByHzz7JDIrtI6k5bvFsHBBVTLyItF2NRuIRbW4aQBwXj1Hyh2S 8d/G6k1xzk3wUxHxz+JFHBjifU6xuQIDAQAB";

    private static String PRIVATE_KEY = "";
    /**
     *@Author gnn [www.coder520.com]
     *@Date 2018/12/4 16:37
     *@Descrintion  读取私钥字符串
     */
    public static String readPrivateKey(String fileName) throws Exception {
        byte[] data = null;
        try {
            data = Files.readAllBytes(new File(fileName).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dataStr = new String(data);
        try {
            PRIVATE_KEY = dataStr;
        } catch (Exception e) {
        }
        if (PRIVATE_KEY == null) {
            throw new Exception("Fail to read private key");
        }
        return PRIVATE_KEY;
    }
    /**
     *@Author gnn [www.coder520.com]
     *@Date 2018/12/4 16:37
     *@Descrintion  读取公钥字符串
     */
    public static String readPublicKey(String fileName) throws Exception {
        byte[] data = null;
        try {
            data = Files.readAllBytes(new File(fileName).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dataStr = new String(data);
        try {
            PUBLIC_KEY = dataStr;
        } catch (Exception e) {
        }
        if (PUBLIC_KEY == null) {
            throw new Exception("Fail to read public key");
        }
        return PUBLIC_KEY;
    }
    /**
 *@Author gnn [www.coder520.com]
 *@Date 2018/12/4 16:30
 *@Descrintion  公钥加密
 */
    public static byte[] encryptByPublicKey(byte[] data, String pubkey) throws Exception {
        byte[] KeyBytes = Base64Util.decode(pubkey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(KeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }
    /**
     *@Author gnn [www.coder520.com]
     *@Date 2018/12/4 16:31
     *@Descrintion  私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] data, String priKey) throws Exception {
        byte[] KeyBytes = Base64Util.decode(priKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(KeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println(Base64Util.encode(publicKey.getEncoded()));
        System.out.println(Base64Util.encode(privateKey.getEncoded()));

//        String data = "楠";
//        byte[] encrypt = encryptByPublicKey(data.getBytes("UTF-8"),PUBLIC_KEY);
//        byte[] decrypt = decryptByPrivateKey(encrypt);
//        System.out.println(encrypt);
//        System.out.println(new String(decrypt,"UTF-8"));
    }
}
