package com.xpfirst.hdrRouter.Test;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by mac on 2018/4/14.
 */
public class DeadLock {
    private TestClass tc1 = new TestClass();
    private TestClass tc2 = new TestClass();

}
