package org.aj.wxpay.util;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestUtil;

import org.aj.wxpay.exception.WxAesException;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * @Description 辅助微信支付的AES相关的工具
 * @Author aj
 * @Date 2019/6/18 11:28
 **/
public class WxPayAESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";



    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";

    private String  charsetName = "UTF-8";


    private SecretKeySpec secretKeySpec;


    public WxPayAESUtil(String wxKey, String  charsetName) {

       if(StringUtils.isBlank(wxKey)){
           throw new IllegalArgumentException("wxKey must not empty");
       }

      if(StringUtils.isNotBlank(charsetName)){
          this.charsetName = charsetName;
      }
      secretKeySpec = new SecretKeySpec( DigestUtil.md5Hex(wxKey).toLowerCase().getBytes(), ALGORITHM);
    }



    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public  String encryptData(String data)  {
        // 创建密码器
        String encode = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encode = Base64.encode(cipher.doFinal(data.getBytes()), charsetName);
        } catch (Exception e) {
            throw new WxAesException("encryptData error",e);
        }
        return encode;
    }

    /**
     * AES解密【目前是适用于解密微信支付加密的数据】
     *
     * @param base64Data
     * @return
     * @throws Exception
     */
    public  String decryptData(String base64Data)  {
        String s = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            s = new String(cipher.doFinal(Base64.decode(base64Data.getBytes(charsetName))), charsetName);
        } catch (Exception e) {
            throw new WxAesException("decryptData error",e);
        }
        return s;
    }


    /**
     * 参考链接：https://www.cnblogs.com/gne-hwz/p/9553502.html
     * 避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
     * 只在第一次调用decrypt()方法时才new 对象
     */
    private static boolean hasInited = false;

    /**
     * BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错
     */
    public static void init() {
        if (hasInited) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        hasInited = true;
    }






}
