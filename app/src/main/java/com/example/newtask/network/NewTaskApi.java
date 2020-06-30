package com.example.newtask.network;


import com.example.newtask.entities.CarsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewTaskApi {

    @GET("cars")
    Single<CarsResponse> getCars(@Query("page") int page);

}
