package com.example.khlopunov.convertermoney.rx;

import android.util.Log;

import com.example.khlopunov.convertermoney.POJO.Fixerio;
import com.example.khlopunov.convertermoney.interfaces.FixerioAPI;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Anatoly on 29.05.2017.
 */

public class RxJava {
    public static final String FIXERIOURL = "http://api.fixer.io/";

    private static Subscription subscription;
    private static BehaviorSubject<Fixerio> observableModelsList;


    private static Observable<Fixerio> init(String date, String currency1, String currency2) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(FIXERIOURL)
                .build();
        

        FixerioAPI fixerioAPI = retrofit.create(FixerioAPI.class);
        return fixerioAPI.getRate(date, currency1, currency2);
    }

    public static Observable<Fixerio> getValue(String date, String currency1, String currency2) {

        observableModelsList = BehaviorSubject.create();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        Observable<Fixerio> observable = init(date, currency1, currency2);
        subscription = observable.subscribe(fixerio -> {observableModelsList.onNext(fixerio);},
                throwable -> {observableModelsList.onError(throwable);})
               ;
        return observableModelsList;
    }
}
