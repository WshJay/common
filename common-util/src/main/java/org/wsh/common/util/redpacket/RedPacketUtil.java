package org.wsh.common.util.redpacket;

import java.math.BigDecimal;
import java.util.Random;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  红包工具类
 * since Date： 2017-05-10 14:48
 */
public class RedPacketUtil {

    /**
     * 随机金额
     * @param remainSize 剩余数量
     * @param remainAmount 剩余金额
     * @return 随机金额
     */
    public static double randomAmount(int remainSize, double remainAmount) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (remainSize == 1) {
            return (double) Math.round(remainAmount * 100) / 100;
        }
        Random r = new Random();
        // 最小金额
        double min = 0.01;
        // 最大金额
        double max = remainAmount / remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        return money;
    }

    public static void main(String[] args) {
        int remainSize = 1000;
        double remainAmount = 10;
        for (int i = 0; i < 10000; i++) {
            double amount = randomAmount(remainSize, remainAmount);
            System.out.println(amount);
            remainSize--;
            remainAmount = remainAmount - amount;
        }
    }
}
