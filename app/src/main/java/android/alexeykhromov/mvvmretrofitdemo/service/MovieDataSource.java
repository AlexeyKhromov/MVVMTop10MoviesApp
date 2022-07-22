package android.alexeykhromov.mvvmretrofitdemo.service;

import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.model.MovieAPIResponse;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private  MovieApiService movieApiService;
    private Application application;

    public MovieDataSource(MovieApiService movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {

    movieApiService = RetrofitInstance.getService();
    Call<MovieAPIResponse> call = movieApiService.getPopularMoviesWithPaging(
            application.getApplicationContext().getString(R.string.api_key), 1);

    call.enqueue(new Callback<MovieAPIResponse>() {
        @Override
        public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {

            MovieAPIResponse movieAPIResponse = response.body();
            ArrayList<Result> results = new ArrayList<>();
            if (movieAPIResponse != null && movieAPIResponse.getResults() != null){
                results = movieAPIResponse.getResults();
                callback.onResult(results, null, (long) 2);
            }

        }

        @Override
        public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

        }
    });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {



    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Result> callback) {

        movieApiService = RetrofitInstance.getService();
        Call<MovieAPIResponse> call = movieApiService.getPopularMoviesWithPaging(
                application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {

                MovieAPIResponse movieAPIResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieAPIResponse != null && movieAPIResponse.getResults() != null){

                    results = movieAPIResponse.getResults();
                    callback.onResult(results, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

            }
        });

    }
}
