package com.noha.client.httpapi;


import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Path;

@RetrofitClient(serviceId = "admin")
public interface HttpApi {

    @GET("/nacos/getString/{num}")
    String getString(@Path("num") Integer num);

}
