package org.wsh.common.test.secret;

import org.junit.Assert;
import org.junit.Test;
import org.wsh.common.util.secret.DSACoder;
import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-10 16:02
 */
public class DSACoderTest {

    @Test
    public void test() throws Exception {
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        // 构建密钥
        Map<String, Object> keyMap = DSACoder.initKey();

        // 获得密钥
        String publicKey = DSACoder.getPublicKey(keyMap);
        String privateKey = DSACoder.getPrivateKey(keyMap);

        System.err.println("公钥:\r" + publicKey);
        System.err.println("私钥:\r" + privateKey);

        // 产生签名
        String sign = DSACoder.sign(data, privateKey);
        System.err.println("签名:\r" + sign);

        // 验证签名
        boolean status = DSACoder.verify(data, publicKey, sign);
        System.err.println("状态:\r" + status);
        Assert.assertTrue(status);

    }
}
