package com.xpfirst.hdrRouter.aop;

/**
 * Created by mac on 2018/4/13.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
/**
 * 系统日志切面类
 * @author lin.r.x
 *
 */
@Component
@Aspect
public class SystemLogAspect {

    @Before(value = "com.xpfirst.hdrRouter.aop.PointCuts.aopDemo()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect1] before advise");
    }

    @Around(value = "com.xpfirst.hdrRouter.aop.PointCuts.aopDemo()")
    public void around(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("[Aspect1] around advise 1");
        pjp.proceed();
        System.out.println("[Aspect1] around advise2");
    }

    @AfterReturning(value = "com.xpfirst.hdrRouter.aop.PointCuts.aopDemo()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    @AfterThrowing(value = "com.xpfirst.hdrRouter.aop.PointCuts.aopDemo()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After(value = "com.xpfirst.hdrRouter.aop.PointCuts.aopDemo()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }
}