package com.example.test.strategy;

public class SubtractionStrategy implements Strategy {
    @Override
    public int calc(int a, int b) {
        return a-b;
    }
}
