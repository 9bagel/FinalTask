package by.epam.learn.bahlei.finaltask.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DaoLoggerAspect {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Pointcut("call(public !static * by.epam.learn.bahlei.finaltask.dao..*.* (..))")
    public void anyPublicMethod() {
    }

    @Before("anyPublicMethod()")
    public void logMethod(JoinPoint joinPoint) {
        LOGGER.info(String.format("Dao method %s() was called", joinPoint.getSignature().getName()));
    }
}