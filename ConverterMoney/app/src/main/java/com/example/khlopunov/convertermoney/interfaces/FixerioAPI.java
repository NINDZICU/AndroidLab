package com.example.khlopunov.convertermoney.interfaces;

import com.example.khlopunov.convertermoney.POJO.Fixerio;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Anatoly on 29.05.2017.
 */

public interface FixerioAPI {
    @GET("latest")
    Observable<Fixerio> getRate(@Query("date") String date, @Query("base") String fromCurrency, @Query("symbols") String toCurrency);
}
