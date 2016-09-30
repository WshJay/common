package org.wsh.common.test;

import org.junit.Test;
import org.wsh.common.util.shiro.encrypt.HashPwd;


public class HashPwdTest {

	@Test
	public void test(){
		HashPwd.HashPassword hashPassword = HashPwd.encrypt("123456");
		System.out.println("密码:" + hashPassword.password + "密钥:" + hashPassword.salt);
	}
}

