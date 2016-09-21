package com.xxx.common.test;

import org.junit.Test;

import com.xxx.common.util.shiro.encrypt.HashPwd;
import com.xxx.common.util.shiro.encrypt.HashPwd.HashPassword;

public class HashPwdTest {

	@Test
	public void test(){
		HashPassword hashPassword = HashPwd.encrypt("123456");
		System.out.println("密码:" + hashPassword.password + "密钥:" + hashPassword.salt);
	}
}

