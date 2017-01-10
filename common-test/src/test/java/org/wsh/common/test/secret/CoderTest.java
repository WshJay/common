package org.wsh.common.test.secret;

import org.junit.Assert;
import org.junit.Test;
import org.wsh.common.util.secret.Coder;

import java.math.BigInteger;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-10 13:58
 */
public class CoderTest {

    @Test
    public void test() throws Exception {
        String inputStr = "123456";
        System.err.println("原文:" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = Coder.encryptBASE64(inputData);

        System.err.println("BASE64加密后:" + code);

        byte[] output = Coder.decryptBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:" + outputStr);

        // 验证BASE64加密解密一致性
        Assert.assertEquals(inputStr, outputStr);

        // 验证MD5对于同一内容加密是否一致
        Assert.assertArrayEquals(Coder.encryptMD5(inputData), Coder
                .encryptMD5(inputData));

        // 验证SHA对于同一内容加密是否一致
        Assert.assertArrayEquals(Coder.encryptSHA(inputData), Coder
                .encryptSHA(inputData));

        String key = Coder.initMacKey();
        System.err.println("Mac密钥:" + key);

        // 验证HMAC对于同一内容，同一密钥加密是否一致
        Assert.assertArrayEquals(Coder.encryptHMAC(inputData, key), Coder.encryptHMAC(
                inputData, key));

        BigInteger md5 = new BigInteger(Coder.encryptMD5(inputData));
        System.err.println("MD5:" + md5.toString(16));

        BigInteger sha = new BigInteger(Coder.encryptSHA(inputData));
        System.err.println("SHA:" + sha.toString(32));

        BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:" + mac.toString(16));
    }
}
