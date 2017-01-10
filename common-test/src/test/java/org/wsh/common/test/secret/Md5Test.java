package org.wsh.common.test.secret;

import org.junit.Test;
import org.wsh.common.util.secret.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-10 14:01
 */
public class Md5Test {

    @Test
    public void encrypt(){
        try {
            String s = "123456";
            System.out.println(MD5.encrypt(s));
            System.out.println(MD5.EncoderPwdByMd5(s));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
