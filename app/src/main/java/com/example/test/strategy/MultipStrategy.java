package com.example.test.strategy;

public class MultipStrategy implements Strategy {
    @Override
    public int calc(int a, int b) {
        return a*b;
    }
}
