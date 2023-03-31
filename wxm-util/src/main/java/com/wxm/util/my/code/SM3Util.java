package com.wxm.util.my.code;



import com.wxm.base.exception.EncodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


/**
 * <p>国密SM3加密 </p>
 * <p>静态方法</p>
 *
 * @author 王森明
 * @date 2021/4/1 11:21
 * @since 1.0.0
 */
@Slf4j
public class SM3Util {
    private static final String SM3_SALT="KING-YUAN-JUN";
    /**
     * 位置必须在字符串长度以内
     */
    private static final int SM3_SALT_POSITION=1;

    public static String encode(String plain) throws EncodeException {
        try{
            return com.wxm.util.my.code.SM3.digestHex(plain);
        }catch (Exception e){
            log.error("加密异常：{}",e.getMessage(),e);
            throw new EncodeException("加密异常："+e.getMessage());
        }
    }

    public static String encode(byte[] bytes) throws EncodeException {
        try{
            return com.wxm.util.my.code.SM3.digestHexBytes(bytes);
        }catch (Exception e){
            log.error("加密异常：{}",e.getMessage(),e);
            throw new EncodeException("加密异常："+e.getMessage());
        }
    }

    public static String encodeWithSalt(String plain) throws  EncodeException {
        try{
            return com.wxm.util.my.code.SM3.digestHex(plain+SM3_SALT);
        }catch (Exception e){
            log.error("加密异常：{}",e.getMessage(),e);
            throw new EncodeException("加密异常："+e.getMessage());
        }
    }

    public static String encodeWithSalt(String plain,String salt) throws   EncodeException {
        try{
            salt= !StringUtils.hasLength(salt)?SM3_SALT:salt;
            return com.wxm.util.my.code.SM3.digestHex(plain+salt);
        }catch (Exception e){
            log.error("加密异常：{}",e.getMessage(),e);
            throw new EncodeException("加密异常："+e.getMessage());
        }
    }
    public static String encodeWithSalt(String plain,String salt,int saltPosition) throws  EncodeException {
        try{
            salt=!StringUtils.hasLength(salt)?SM3_SALT:salt;
            saltPosition=saltPosition>plain.length()?SM3_SALT_POSITION:saltPosition;
            return com.wxm.util.my.code.SM3.digestHex(plain.substring(0,saltPosition)+salt+plain.substring(saltPosition));
        }catch (Exception e){
            log.error("加密异常：{}",e.getMessage(),e);
            throw new EncodeException("加密异常："+e.getMessage());
        }
    }


    public static void main(String[] args) throws EncodeException {
        log.info("wxm:{}",encode("wxm"));
        log.info("wsm:{}",encode("wsm"));
        log.info("wxy:{}",encode("wxy"));
        log.info("wangxm:{}",encode("wangxm"));
        log.info("wangsm:{}",encode("wangsm"));
        log.info("wangxy:{}",encode("wangxy"));
        log.info("admin:{}",encode("admin"));
    }
}
