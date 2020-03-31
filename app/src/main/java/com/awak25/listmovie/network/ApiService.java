package com.awak25.listmovie.network;

import com.awak25.listmovie.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<ResponseMovie> getMovie (@Query("api_key") String apikey,
                                  @Query("languange") String languange,
                                  @Query("page") int page);

}