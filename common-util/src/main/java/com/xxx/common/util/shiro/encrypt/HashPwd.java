package com.xxx.common.util.shiro.encrypt;

/**
 * 密码加密 并返回HashPassword对象
 * Project:     <common-util>
 * File Name:   <HashPassword.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-4-1 下午4:27:18
 */
public class HashPwd {
	
	public static final int INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	/**
	 * 密码加密 并返回HashPassword对象.
	 */
	public static HashPassword encrypt(String plainText) {
		HashPassword result = new HashPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		result.salt = Encodes.encodeHex(salt);
		byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
		result.password = Encodes.encodeHex(hashPassword);
		return result;

	}

	public static class HashPassword {
		public String salt;
		public String password;
	}
}

