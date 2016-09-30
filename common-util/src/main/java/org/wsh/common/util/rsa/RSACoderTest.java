package org.wsh.common.util.rsa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;  
  




import java.util.Map;  
import java.util.TreeMap;
  
/**
 * RSA加密
 * 
 * Project:     <common-util>
 * File Name:   <RSACoderTest.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月21日 下午7:17:22
 */
public class RSACoderTest {  
    private String publicKey;  
    private String privateKey;  
  
    @Before  
    public void setUp() throws Exception {  
        Map<String, Object> keyMap = RSACoder.initKey();  
  
//        publicKey = RSACoder.getPublicKey(keyMap);  
//        privateKey = RSACoder.getPrivateKey(keyMap); 
        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgvc2YFaEnp1S8fNTdnJk4gOlqHYobE0HzEsfVX4UAwd3zv/ebW0SHtBIrhLK9M89uvJYgXXslPElMhn2xeqrf5MIXPawptL6ERyWFJ1iWtPTWVGBlY3XH8SHJ7ZRnxs9yR0zrEm4NUYCe/qT2C/3QhwqABU78Clnjk0MFan3ByQIDAQAB";
        privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOC9zZgVoSenVLx81N2cmTiA6WodihsTQfMSx9VfhQDB3fO/95tbRIe0EiuEsr0zz268liBdeyU8SUyGfbF6qt/kwhc9rCm0voRHJYUnWJa09NZUYGVjdcfxIcntlGfGz3JHTOsSbg1RgJ7+pPYL/dCHCoAFTvwKWeOTQwVqfcHJAgMBAAECgYA1WFgotVmfU/SlGOxd/gHQi6jb608Qxi1tSRPt7dJgL6RDa1mWor9NhsyZT9ItXtLp8hGgdQ/58qljZLkJG5jbQkYb3op6BMPJdW00uPxgtI5EZqBKk+ldIceBkXq90r7lT7JCLPMXi6wxip6drfDraM4IMN++KiR3WYOR+UwBUQJBAPW8SBs8njNG7iTS48Fsy+aRLrWgXTOr2Ve29iAfUTXdk74tTsEB5F+WtAoKBCdX69gtNXCfXaIM3q4S3r921hcCQQDqIQauee+02Rri9CJALyvA6WYbnKnJsUuxPG4mYlbJ34CwoGcTybEHcYA0Qc+Fv72vbuIymvcLgfwsbisQbvMfAkBbMXlM6k0HWH5OGhk9keOFeEFuU8niwBaTT6m9OAIKTGVkwSmlxM78jk/YHel2tFEo03YuimXUGpSU4xnY0gAXAkB6fxcQ2Il7FJBFqDf3XvbEcxlSXxBSj6mrrJNxD8iazI53WyUzLDasT00lij06nExxalF86qlQ4wx+bpGngnU/AkBsPktGHR+kt2ncEZfy68OABfn+AMqp+Y/vg0QRVAJ+n+U8OAEKPP0TU1SjrKO2DinziMF66VB8wOrdM3+OTUQp";
        System.err.println("公钥: \n\r" + publicKey);  
        System.err.println("私钥： \n\r" + privateKey);  
    }  
  
//    @Test  
//    public void test() throws Exception {  
//        System.err.println("公钥加密——私钥解密");  
//        String inputStr = "abc";  
//        byte[] data = inputStr.getBytes();  
//  
//        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);  
//        String a = encodedData.toString();
//        System.err.println(a);  
//        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,  
//                privateKey);  
//  
//        String outputStr = new String(decodedData);  
//        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
//        assertEquals(inputStr, outputStr);  
//  
//    }  
  
    @Test  
    public void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
        String inputStr = "{userId:1,orderId:3}";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);  
  
//        byte[] decodedData = RSACoder  
//                .decryptByPublicKey(encodedData, publicKey);  
//  
//        String outputStr = new String(decodedData);  
//        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
//        assertEquals(inputStr, outputStr);  
  
        System.err.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = RSACoder.sign(encodedData, privateKey);  
        System.err.println("签名:\r" + sign);
//        String a = Base64.encodeBase64URLSafeString(sign.getBytes("utf-8"));
//        System.out.println();
//        System.out.println(a);
//        System.out.println();
//        String token = Base64Util.decompressData(a);
//        System.out.println(token);
        // 验证签名  
        boolean status = RSACoder.verify(encodedData, publicKey, sign);  
        System.err.println("状态:\r" + status);  
        assertTrue(status);  
        StringBuffer keyStr = new StringBuffer();
        StringBuffer valStr = new StringBuffer();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("appNo","3.0.5");
        map.put("deviceNo","865473028153594");
        map.put("cityId","3");
        map.put("partyId","117506");
        map.put("createTime","201604221012");
        map.put("secretKey","WEAaZDZ1WY30Y1Sp4XWMURb9wYJrJKFr");
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	keyStr.append(entry.getKey());
        	valStr.append(entry.getValue());
		}
        System.out.println(keyStr.append("=").append(valStr).toString());
        System.out.println(MD5.MD5Encode(keyStr.append("=").append(valStr).toString()));
    }  
  
}  

