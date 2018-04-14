package com.xpfirst.hdrRouter.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mac on 2018/4/14.
 */
public class TestClass {
    private Logger logger = LoggerFactory.getLogger(TestClass.class);
    private static TestClass testClass = null;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private String testStr = "test";
    public TestClass(){
        logger.info("开始了");
    }
    //单例和同步
    public static synchronized TestClass getDesUtils(){
        if (testClass == null){
            testClass = new TestClass();
        }
        return testClass;
    }
    //Lock锁
    public String getStr(){
        reentrantLock.lock();
        testStr += "123";
        reentrantLock.unlock();
        return testStr;
    }
    //synchronize锁
    public String  getStr1(){
        synchronized(testStr){
            testStr += "123";
        }
        return testStr;
    }
}
