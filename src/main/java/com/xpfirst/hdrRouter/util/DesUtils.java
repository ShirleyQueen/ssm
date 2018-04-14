/**
 * Copyright (C) 2013 北京学信科技有限公司
 *
 * @className:com.xuexin.bizserver.util.DesUtils
 * @version:v1.0.0 
 * @author:李大鹏
 * 
 * Modification History:
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2013-7-16       李大鹏                        v1.0.0        create
 *
 */
package com.xpfirst.hdrRouter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class DesUtils {
    private static final Logger Log = LoggerFactory.getLogger(DesUtils.class);
    private static DesUtils signleDes = null;
    private static Key key = null;

    static {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂\
            DESKeySpec keySpec = new DESKeySpec("Xhr15T8dGd44IU04bd9EfHc9cF782Yt9".getBytes("utf-8"));
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }

    }

    /**
     * 解密
     * 
     * @param data
     * @return
     * @throws Exception
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-7-17 下午1:42:55
     */
    public static String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] pasByte = deCipher.doFinal(Base64.decode(data));
        return new String(pasByte, "UTF-8");
    }
}
