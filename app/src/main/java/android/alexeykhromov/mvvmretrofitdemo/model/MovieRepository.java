package android.alexeykhromov.mvvmretrofitdemo.model;

import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.service.MovieApiService;
import android.alexeykhromov.mvvmretrofitdemo.service.RetrofitInstance;
import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;


    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData(){
        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<MovieAPIResponse> call = movieApiService
                .getPopularMovies(application.getApplicationContext().getString(R.string.api_key), "ru");


        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {
                MovieAPIResponse movieApiResponse =
                        response.body();

                if (movieApiResponse != null && movieApiResponse.getResults() != null){
                    results =  movieApiResponse.getResults();

                    mutableLiveData.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
