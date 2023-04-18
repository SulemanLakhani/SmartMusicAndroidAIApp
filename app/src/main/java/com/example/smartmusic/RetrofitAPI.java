package com.example.smartmusic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * RetrofitAPI.java
 * @author Suleman, Austin, Patrick
 * this java class file is an interface and uses the Retrofit API
 * Date: 04/28/22
 */
public interface RetrofitAPI {

    @GET()
    /**
     * calls the API and returns a response from the API
     */
    Call<MessageModal> getMessage(@Url String url);

}
