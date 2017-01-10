package org.wsh.common.test.secret;

import org.junit.Before;
import org.junit.Test;
import org.wsh.common.util.secret.Coder;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-10 11:43
 */
public class Base64Test {

    private String secretValue;

    @Before
    public void encrypt(){
        try {
            secretValue = "123456";
            secretValue = Coder.encryptBASE64(secretValue.getBytes());
            System.out.println(secretValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decrypt(){
        try {
            secretValue = new String(Coder.decryptBASE64(secretValue));
            System.out.println(secretValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
