package by.epam.learn.bahlei.finaltask.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class DaoLoggerAspect {

    @Pointcut("call(public * by.epam.learn.bahlei.finaltask.dao..*.* (..))")
    public void anyPublicMethod() {
    }

    @Before("anyPublicMethod()")
    public void logMethod(JoinPoint joinPoint) {
        Logger LOGGER = LogManager.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message += " " + Arrays.toString(args);
        }
        LOGGER.info(message);
        System.out.println(message);
    }
}
