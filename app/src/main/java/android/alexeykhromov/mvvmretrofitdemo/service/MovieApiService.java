package android.alexeykhromov.mvvmretrofitdemo.service;

import android.alexeykhromov.mvvmretrofitdemo.model.MovieAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MovieAPIResponse> getPopularMovies(@Query("api_key") String apiKey,@Query("language") String lang);
    @GET("movie/popular")
    Call<MovieAPIResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey,@Query("page") long page);
}
