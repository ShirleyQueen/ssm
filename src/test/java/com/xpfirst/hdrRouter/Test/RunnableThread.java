package com.xpfirst.hdrRouter.Test;

/**
 * Created by mac on 2018/4/14.
 */
public class RunnableThread {

    public static void testRunnable(){
        Runnable runnable = new Runnable (){
            int tmpi = 10;
            @Override
            public void run() {
                tmpi += 19;
                System.out.print("tmpi = " + tmpi);
            }
        };
        new Thread(runnable).start();
    }
    public static void main(String[] args){
        RunnableThread.testRunnable();
    }
}
