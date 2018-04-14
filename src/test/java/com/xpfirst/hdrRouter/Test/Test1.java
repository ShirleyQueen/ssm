package com.xpfirst.hdrRouter.Test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xpfirst.hdrRouter.util.DesUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2018/1/30 下午2:06
 **/
public class Test1 {
        private static final Logger log = LoggerFactory.getLogger(com.xpfirst.hdrRouter.Test.Test1.class);

        public static void main(String[] args){

             String s1 = "121";
             String s2 = new String("121");

             System.out.print(s1 ==  s2);
             System.out.print(s1.equals(s2));

            StringBuffer sb = new StringBuffer();
            sb.append("1");
        }
}

