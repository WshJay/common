package com.xxx.common.util.rand;

import java.util.Random;

public class RandomUtil {
	
	/**
	 * 生成随机数
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public static int createRandNum(int beginNum, int endNum){
		return new Random().nextInt(endNum - beginNum + 1) + beginNum;
	}
}

