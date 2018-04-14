package com.xpfirst.hdrRouter.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by mac on 2018/4/14.
 */
public class CallableThread{


    //callable
    public static void testCallAble() {
        Callable<Integer> callable = new Callable<Integer>() {
            Integer tmpI = 10;
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return tmpI += 100;
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        System.out.println("结束了");
        try {
            System.out.println("tmpI = " + future.get());
            System.out.println("结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println("11111\n");
        CallableThread.testCallAble();
        System.out.println("222222\n");
    }
}
