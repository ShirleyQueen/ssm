package com.xpfirst.hdrRouter.proxy;

/**
 * Created by mac on 2018/4/13.
 */
public class UserProxy implements UserInterface{
    private UserImpl userImpl;
    public UserProxy(UserImpl ui){
        this.userImpl = ui;
    }

    @Override
    public void myName() {
        System.out.print("\n12314");
        this.userImpl.myName();
        System.out.print("\n1231423242424");
    }
    public static void main(String args[]){
        UserImpl userImpl = new UserImpl();
        UserProxy userProxy = new UserProxy(userImpl);
        userProxy.myName();
    }
}
