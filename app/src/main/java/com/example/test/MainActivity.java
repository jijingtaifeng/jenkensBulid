package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.test.databinding.ActivityMainBinding;
import com.example.test.mvvm.MyViewModel;
import com.example.test.picker.MaintainPayMethodEntity;
import com.example.test.proxy.BuyA;
import com.example.test.proxy.DynamicProxy;
import com.example.test.proxy.Subject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
        initRxjava();
        initProxy();
        initMvvm();
        initPick();
    }

    private void initPick() {
         List<String> payMethodEntities = new ArrayList<>(); //支付方式
        payMethodEntities.add("ss");
        payMethodEntities.add("sll");
       // payMethodEntities.add(new MaintainPayMethodEntity(1,"wee"));
       // payMethodEntities.add(new MaintainPayMethodEntity(2,"wedde"));
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
            }
        }).setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(payMethodEntities);
        pvOptions.show();
    }

    private void initMvvm() {


        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);

    }

    private void initProxy() {

        DynamicProxy dynamicProxy = new DynamicProxy();

        BuyA buyA = new BuyA("小叶");
        Subject proxyInstance = (Subject) dynamicProxy.newProxyInstance(buyA);
        proxyInstance.buybuy();


    }

    private void initRxjava() {
        //被观察者
        final Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("我是第一名");
                emitter.onNext("我是第二名");
                emitter.onNext("我是第三名");
                emitter.onComplete();
            }
        });


        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("lybj", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("lybj", "onNext" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("lybj", "onError" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("lybj", "onComplete");
            }
        };


   //     observable.subscribe(observer);

        Observable.just("1","2").flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                if (s.equals("2")){
                    return Observable.just("3");
                }
                return Observable.just(s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("lybj", s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("lybj", throwable.getMessage());
            }
        });




    }
}
