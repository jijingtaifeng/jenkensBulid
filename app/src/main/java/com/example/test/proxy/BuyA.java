package com.example.test.proxy;

public class BuyA implements Subject {

    public String name;
    public BuyA(String name) {
        this.name= name;
    }

    @Override
    public void buybuy() {
        System.out.println(name+"要买Mac");
    }
}
