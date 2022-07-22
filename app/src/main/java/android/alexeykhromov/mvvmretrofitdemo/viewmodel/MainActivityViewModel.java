package android.alexeykhromov.mvvmretrofitdemo.viewmodel;

import android.alexeykhromov.mvvmretrofitdemo.model.MovieRepository;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Result>> getAllMovieData(){

        return movieRepository.getMutableLiveData();
    }

}
