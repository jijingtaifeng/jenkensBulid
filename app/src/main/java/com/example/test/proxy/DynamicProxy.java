package com.example.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object mProxyObject;

    public Object newProxyInstance(Object proxyObject){
        this.mProxyObject =proxyObject;
        return Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(),
                proxyObject.getClass().getInterfaces(),this);

    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("代购出门了");
        Object result = null;
        result = method.invoke(mProxyObject, objects);
        return result;
    }
}
