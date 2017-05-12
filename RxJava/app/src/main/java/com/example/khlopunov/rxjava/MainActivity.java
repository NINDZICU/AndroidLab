package com.example.khlopunov.rxjava;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener((view) -> {

        });

        Observable<Integer> observable = Observable.just(1, 2, 4);
        observable.subscribe(value -> {
            System.out.println("VALUE = " + value);
        }, throwable -> {
            //do nothing
        }, () -> {
            //do nothing
        });

        Observable<Integer> obs1 = Observable.just(1, 4, 23, 5, 57, 21, 75, 324, 123, 758);
        Observable<Integer> obs2 = Observable.just(32, 18, 76);
        Observable<Integer> mergedObs = Observable.merge(obs1, obs2);
        mergedObs.filter(value -> value < 76)
                .filter(value -> value > 4)
                .map(value -> value * value)
                .map(value -> value / 4)
                .take(5)
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> System.out.println(result));
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//            }
//            @Override
//            public void onError(Throwable e) {
//            }
//            @Override
//            public void onNext(Integer integer) {
//                Log.i("TAG", String.valueOf(integer));
//            }
//        });
        List<String> names = new ArrayList<>();
        names.add("Ilmaz");
        names.add("Ilma1z");
        names.add("Ilmaz2");
        names.add("Ilm2az");
        names.add("maz");
        names.add("az");
        countNames(names, "l").subscribe(result -> System.out.println("Count:" + result));
        List<Integer> digits = new ArrayList<>();
        digits.add(12);
        digits.add(2);
        digits.add(5);
        sum(digits).subscribe(result -> System.out.println("SUM: " + result));

    }

    //Task1
    private Observable<Integer> countNames(@NonNull List<String> names, @NonNull String whatToSearch) {
        Observable<Integer> obs = Observable.from(names)
                .map(String::toLowerCase)
                .filter(value -> value.contains(whatToSearch))
                .count();
//                .flatMap(value -> {
//                    if (value.contains(whatToSearch)) {
//                        return Observable.just(1);
//                    } else {
//                        return Observable.just(0);
//                    }
//                });
        return obs;
    }

    private Observable<Integer> sum(@NonNull List<Integer> digits) {
        Observable<Integer> obs = Observable.from(digits);
        return MathObservable.sumInteger(obs);
    }

    private Observable<Integer> createObservable() {
        return Observable.create(subscriber -> {
            subscriber.onNext(15);
            subscriber.onNext(16);
            subscriber.onNext(25);
            subscriber.onNext(29);
            int random = new Random().nextInt(2);
            if(random == 1){
                subscriber.onNext(55);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //todo отписываться unsubcsrib...
    }
}
