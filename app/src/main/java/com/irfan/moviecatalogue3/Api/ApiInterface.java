package com.irfan.moviecatalogue3.Api;

import com.irfan.moviecatalogue3.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<Movie> getMovie(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("discover/tv")
    Call<Movie> getTv(@Query("api_key") String apiKey, @Query("language") String language);

}
