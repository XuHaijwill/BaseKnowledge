package cn.enjoy.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenManager {

    private final static Logger log = LoggerFactory.getLogger(TokenManager.class);
    //私钥
    //private static final String SECRET = "Axmk89Li3Aji9M";

    //过期时间1分钟
    private static final int expiresTime = 60000;

    public static String createToken(String userId,String secret){
        //获取加上过期时间后的时间
        Date nowDate = new Date();
        Date expiresDate = new Date(System.currentTimeMillis()+expiresTime);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)	//请求头
                .withClaim("userId", null==userId?null:userId) //存储信息，用户ID
                .withIssuedAt(nowDate)		//当前时间
                .withExpiresAt(expiresDate)		//过期时间
                .sign(Algorithm.HMAC256(secret));		//私钥

        return token;
    }

    public static boolean verifyToken(String secret,String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            DecodedJWT verify = verifier.verify(token);
            //能返回数据集合(用于在jwt中存储一些数据)的，但是由于这一版本只需要核验token是否合法，所以只需要返回true和false；
            System.out.println(verify.getClaims());
            return true;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return false;
        }
    }
}