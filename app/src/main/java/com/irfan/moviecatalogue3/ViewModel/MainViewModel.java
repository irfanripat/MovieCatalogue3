package com.irfan.moviecatalogue3.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.irfan.moviecatalogue3.Api.ApiInterface;
import com.irfan.moviecatalogue3.Model.Movie;
import com.irfan.moviecatalogue3.Model.Result;
import com.irfan.moviecatalogue3.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "9d7f6bbc95d4db740e5763a39a73a659";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private String LANG = "en-US";
    private MutableLiveData<ArrayList<Result>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Result>> listTv = new MutableLiveData<>();

    public LiveData<ArrayList<Result>> getListMovie(Context context) {
        setListMovie(context);
        return listMovie;
    }

    public LiveData<ArrayList<Result>> getListTv(Context context) {
        setListTv(context);
        return listTv;
    }

    private void setListMovie(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (Locale.getDefault().getLanguage().equals(new Locale("id").getLanguage())) {
            LANG = "id-ID";
        } else {
            LANG = "en-US";
        }

        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Movie> call = apiInterface.getMovie(API_KEY, LANG);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                listMovie.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(context, R.string.toast_massage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListTv(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Movie> call = apiInterface.getTv(API_KEY, LANG);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                listTv.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(context, R.string.toast_massage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
