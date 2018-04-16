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
    private String testStr = "";

    public TestClass(){
        logger.info("开始了");
    }
    //单例和同步
    public static synchronized TestClass getTestClass(){
        if (testClass == null){
            testClass = new TestClass();
        }
        return testClass;
    }
    //Lock锁
    public String getStr0()  {
        reentrantLock.lock();

        try {
            while (true){
                testStr += "123";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        testStr += "123";
        reentrantLock.unlock();
        System.out.print("====="+testStr);
        return testStr;
    }
    //Lock锁
    public String getStr1() throws InterruptedException {
        System.out.print("\n=====getStr1 start");

        reentrantLock.lockInterruptibly();

        try {
            while (true){
                testStr += "123";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        testStr += "00000";
//        reentrantLock.unlock();
        System.out.print("\n====="+testStr);
        return testStr;
    }
    //synchronize锁
    public String  getStr2(){
        synchronized(testStr){
            testStr += "11111";
        }
        System.out.print("\n====="+testStr);
        return testStr;
    }
    public String  getStr3(){
        reentrantLock.lock();
        testStr += "4444";
        reentrantLock.unlock();
        System.out.print("====="+testStr);
        return testStr;
    }

    public static void main(String[] args){
        final TestClass testClass = new TestClass();

//        new Threadd(){
//            @Override
//            public void run(){
//                testClass.getStr0();
//
//            }
//        }.start();

        final Thread tmpt =  new Thread(){
            @Override
            public void run(){
                try {
                    testClass.getStr1();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    System.out.print("\n=====12342535");
                }
                finally{

                }
            }
        };
        tmpt.start();

        tmpt.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testClass.getStr3();
//        new Thread(){
//            @Override
//            public void run(){
//                tmpt.interrupt();
//            }
//        }.start();
    }
}
