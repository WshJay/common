package org.wsh.common.provider.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wsh.common.util.logger.LoggerService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Spring AOP
 * since Date： 2016/10/19 16:10
 */
@Component
@Aspect
public class ExpendAdvice extends LoggerService {

    //    @Pointcut("execution(* com.trc.funds.*.service.impl.*.do*(..)) || execution(* com.trc.funds.*.service.impl.*.select*(..)))")
    @Pointcut("execution(* org.wsh.common.service.impl.*.*.*(..))")
    private void aspectjMethod() {
    }


//    @Before("aspectjMethod()")
//    public void beforeAdvice(JoinPoint joinPoint) {
//        System.out.println("-----beforeAdvice().invoke-----");
//        System.out.println(System.currentTimeMillis());
//        System.out.println("-----End of beforeAdvice()------");
//    }
//
//    @After("aspectjMethod()")
//    public void afterAdvice(JoinPoint joinPoint) {
//        System.out.println("-----afterAdvice().invoke-----");
//        System.out.println(System.currentTimeMillis());
//        System.out.println("-----End of afterAdvice()------");
//    }

    @Around("aspectjMethod()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String className = joinPoint.getThis().toString();
            String methodName = joinPoint.getSignature().getName();
            Object[] objects = joinPoint.getArgs();
            StringBuilder params = new StringBuilder();
            if (objects.length > 0){
                for (Object object : objects) {
                    params.append(JSON.toJSONString(object));
                }
            }
            long st = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            logger.info("[***类名:" + className + " ,方法名:" + methodName + " ,传入参数:" + params + " ,共计消耗:" + (System.currentTimeMillis() - st) + " ms ***]");
            return result;
        } catch (Throwable throwable) {
            logger.error("环绕增强发生异常!请联系超级管理员");
            logger.error(throwable.getMessage(), throwable.getStackTrace());
            throw throwable;
        }
    }

//    @AfterReturning(value = "aspectjMethod()", returning = "retVal")
//    public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {
//        System.out.println("-----afterReturningAdvice().invoke-----");
//        System.out.println("Return Value: " + retVal);
//        System.out.println(" 此处可以对返回值做进一步处理");
//        System.out.println(" 可通过joinPoint来获取所需要的内容");
//        System.out.println("-----End of afterReturningAdvice()------");
//    }
//
//    @AfterThrowing(value = "aspectjMethod()", throwing = "ex")
//    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
//        System.out.println("-----afterThrowingAdvice().invoke-----");
//        System.out.println(" 错误信息："+ex.getMessage());
//        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
//        System.out.println(" 可通过joinPoint来获取所需要的内容");
//        System.out.println("-----End of afterThrowingAdvice()------");
//    }
}
