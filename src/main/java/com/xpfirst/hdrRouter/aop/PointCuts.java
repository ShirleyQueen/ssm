package com.xpfirst.hdrRouter.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut(value = "within(com.xpfirst.hdrRouter.action.*)")
    public void aopDemo() {
        System.out.print("fasdfasfafdasfd");
    }
}
