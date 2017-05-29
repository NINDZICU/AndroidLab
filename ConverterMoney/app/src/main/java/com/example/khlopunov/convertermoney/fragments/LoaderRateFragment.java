package com.example.khlopunov.convertermoney.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.khlopunov.convertermoney.POJO.Rates;
import com.example.khlopunov.convertermoney.interfaces.TaskInterface;
import com.example.khlopunov.convertermoney.rx.RxJava;

import java.lang.reflect.InvocationTargetException;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin on 23.12.2016.
 */

public class LoaderRateFragment extends Fragment {
    private TaskInterface mTaskInterface;
    private Subscription subscription;

//    public boolean isRunning(){
//        return myAsync!=null;
//    }


    @Override
    public void onAttach(Context context) {
        setInterface(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        setInterface(activity);
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void getRate(String date, String currency1, String currency2) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = RxJava.getValue(date, currency1, currency2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    if (currency1.equals(currency2)) {
                        mTaskInterface.onTaskFinish("1 " + currency1 + " = 1 " + currency2);
                    } else {
                        mTaskInterface.onTaskStarted();
                        try {
                            Rates rates = value.getRates();
                            mTaskInterface.onTaskFinish("1 " + value.getBase() + " = " + rates.getClass().getMethod("get" + currency2).invoke(rates) + " " + currency2);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }, throwable -> mTaskInterface.onTaskFinish("Что то пошло не так, проверь свое интернет соединение и  что год не меньше 1999, если все правильно то для этой даты нет записи"));
    }
    //TODO сделать retain чтобы не пересоздавался, и поворот проверить

    private void setInterface(Context context) {
        if (context instanceof TaskInterface) {
            mTaskInterface = (TaskInterface) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mTaskInterface = null;
    }

    public void stopAsync() {
        //TODO возможно не так
        subscription.unsubscribe();
    }
}

