package cn.coderme.cblog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

public class JwtUtils {

    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static final String APP_SECRET = "YWJjZA==";// abcd

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static Key getKeyInstance() {
        //        return MacProvider.generateKey(); //We will sign our JavaWebToken
        // with our ApiKey secret
        final Base64.Decoder decoder = Base64.getDecoder();

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = decoder.decode(APP_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    /**
     * 生成 jwt
     *
     * @param claims
     * @return
     */
    public static String createJavaWebToken(String id, String subject, Map<String, Object> claims, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        //下面就是在为payload添加各种标准声明和私有声明了
        // iss: jwt签发者
        // sub: jwt所面向的用户
        // aud: 接收jwt的一方
        // exp: jwt的过期时间，这个过期时间必须要大于签发时间
        // nbf: 定义在什么时间之前，该jwt都是不可用的.
        // iat: jwt的签发时间
        // jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims) //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id) //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now) //iat: jwt的签发时间
                .setSubject(subject) //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(SignatureAlgorithm.HS256, getKeyInstance());//设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp); //设置过期时间
        }
        return builder.compact();
    }

    /**
     * 校验
     *
     * @param jwt
     * @return
     */
    public static Claims verifyJavaWebToken(String jwt) {
        try {
            Claims jwtClaims = Jwts.parser().setSigningKey(getKeyInstance())
                    .parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("json web token verify failed");
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

        String ab = JwtUtils.createJavaWebToken(UUID.randomUUID().toString(), "", new HashMap<>(), 10000);
        System.out.println(ab);
//        Thread.sleep(2000);
        Map<String, Object> result = JwtUtils.verifyJavaWebToken(ab);
        System.out.println(result);
    }

    public void base64() throws Exception {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "abcd";
        final byte[] textByte = text.getBytes("UTF-8"); //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText); //解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    }
}