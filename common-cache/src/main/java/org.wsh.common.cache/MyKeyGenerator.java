package org.wsh.common.cache;

import org.springframework.cache.interceptor.DefaultKeyGenerator;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  自定义主键策略
 * since Date： 2016-11-24 10:03
 */
public class MyKeyGenerator extends DefaultKeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        // Object keyGenerator = super.generate(target, method, params);

//        StringBuffer buffer = new StringBuffer();
//        Class entityClass = GenericsHelper.getSuperGenericsClass(target.getClass());
//        buffer.append(entityClass.getName());
//        if (params != null && params.length > 1) {
//            for (Object obj : params) {
//                if (obj != null) {
//                    if (obj instanceof AtomicInteger || obj instanceof AtomicLong || obj instanceof BigDecimal
//                            || obj instanceof BigInteger || obj instanceof Byte || obj instanceof Double
//                            || obj instanceof Float || obj instanceof Integer || obj instanceof Long
//                            || obj instanceof Short) {
//                        buffer.append(obj);
//                    } else if (obj instanceof List || obj instanceof Set || obj instanceof Map) {
//                        buffer.append(obj);
//                    } else {
//                        buffer.append(obj.hashCode());
//                    }
//                }
//            }
//        }
//        System.out.println("key-buffer:" + buffer.toString());
//        return buffer.toString().hashCode();
        return null;
    }

}
