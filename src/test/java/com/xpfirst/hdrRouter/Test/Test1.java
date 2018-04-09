package com.xpfirst.hdrRouter.Test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        static int tmpInt = 123;
        @Test
        //发送验证码,接口有bug,获取验证码,并不需要使用
        public void testStatic() {

        }
        public void testStatic1() {
        }
//        洗牌算法
        @Test
        public void xipai(){
                List<Integer> cards = new ArrayList<>();
                for (int i = 0; i < 52; i++){
                        cards.add(i);
                }
                List<Integer> newCards = new ArrayList<>();
                for (int i = 0; i < 52; i++){
                        Double tmpD = Math.random();
                        System.out.print("\n Math.random() =" + tmpD);
                        int index = (int)(tmpD * 52);
                        Integer tmpInt = cards.get(index);
                        Integer tmpI = cards.get(i);
                        cards.set(i,tmpInt);
                        cards.set(index,tmpI);
                }
                System.out.print("\n fasdf=" + cards.toString());
        }

        public void testCollection(){
                List<Integer> tmpList = new ArrayList<>();
                Set<Integer> tmpSet = new HashSet<>();
                Map<String,String> tmpMap = new HashMap<>();

                Collections.sort(tmpList);
                TreeSet<Integer> tmptset = new TreeSet<>();
                tmptset.wait();
        }
}
