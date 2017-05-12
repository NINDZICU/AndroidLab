package ru.gdgkazan.rxjavasamples.tasks;

import android.support.annotation.NonNull;

import java.math.BigInteger;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask6 {

    /**
     * TODO :
     * <p>
     * Create the stream of integers [1..100000] and apply next functions:
     * 1) Multiply all elements by 2
     * 2) Remove 40 000 elements from start and 40 000 elements from end
     * 3) Remove all values which is not divided by 3
     * 4) Find multiplication of all values in the stream and transform into one single BigInteger
     * 5) Operations above are rather slow. Try to calculate result in observable only once.
     * You code will be also tested for speed - you shouldn't recalculate result for each new subscriber.
     */
    @NonNull
    public static Observable<BigInteger> task6Observable() {
        Observable<BigInteger> obs = Observable.range(1, 100000)
                .map(value -> value*2)
                .skip(40000)
                .skipLast(40000)
                .filter(value -> value%3==0)
                .cache()
//                .flatMap(value ->Observable.just(BigInteger.valueOf(value)))  //медленно
//                .reduce(BigInteger::multiply)
                .reduce(BigInteger.ONE, ((bigInteger, bigInteger2) -> bigInteger.multiply(BigInteger.valueOf(bigInteger2))))
                ;
        return obs;
    }

}