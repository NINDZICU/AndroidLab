package interfaces;

import com.example.khlopunov.serviceexample.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anatoly on 17.03.2017.
 */

public interface RandomUserApi {
    String RESULTS ="10";

    @GET("api")
    Call<Example> getUsers(@Query("results") String results);
}
