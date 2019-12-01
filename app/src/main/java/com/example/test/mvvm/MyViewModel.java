package com.example.test.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> a;
    private MutableLiveData<Integer> b;

    public MutableLiveData<Integer> getA(){
        if (a==null){
            a = new MutableLiveData<>();
            a.setValue(0);
        }

        return a;
    }


    public MutableLiveData<Integer> getB(){
        if (b==null){
            b = new MutableLiveData<>();
            b.setValue(0);
        }

        return b;
    }



}
