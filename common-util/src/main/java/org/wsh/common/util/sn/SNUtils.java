package org.wsh.common.util.sn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  发号中心
 * since Date： 2017-04-17 19:54
 */
public class SNUtils {

    private static String DEFAULT_BASE = "0123456789";
    private static String DEFAULT_FORMAT = "yyyyMMddHHmmss";
    private static int DEFAULT_LENGTH = 8;

    private static long tradeNO = 0;

    public static long getTradeNO(){
        tradeNO++;
        return tradeNO;
    }

    public SNUtils() {
    }

    public static String generateSNWithPrefix(Enum prefix) {
        return prefix.name().toUpperCase() + getSN(DEFAULT_LENGTH);
    }

    public static String getSN() {
        return getSN(DEFAULT_LENGTH);
    }

    public static String getSN(int length) {
        return getSN(length, DEFAULT_FORMAT);
    }

    public static String getSN(int length, String format) {
        return getSN(length, format, DEFAULT_BASE);
    }

    public static String getSN(int length, String format, String base) {
        Random random = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(new Date());
        StringBuffer sb = new StringBuffer(dateString);

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }
}
