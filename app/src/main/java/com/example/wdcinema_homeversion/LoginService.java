package com.example.wdcinema_homeversion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

}
