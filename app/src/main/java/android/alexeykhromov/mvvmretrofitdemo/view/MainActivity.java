package android.alexeykhromov.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.adapter.ResultAdapter;
import android.alexeykhromov.mvvmretrofitdemo.model.MovieAPIResponse;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.alexeykhromov.mvvmretrofitdemo.service.MovieApiService;
import android.alexeykhromov.mvvmretrofitdemo.service.RetrofitInstance;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getPopularMovies();
       swipeRefreshLayout = findViewById(R.id.swiperRefresh);
       swipeRefreshLayout.setColorSchemeResources(com.google.android.material.R.color.design_default_color_primary);
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               getPopularMovies();
           }
       });

    }

    public void getPopularMovies() {

        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<MovieAPIResponse> call = movieApiService
                .getPopularMovies(getString(R.string.api_key));
        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call,
                                   Response<MovieAPIResponse> response) {

                MovieAPIResponse movieApiResponse =
                        response.body();

                if (movieApiResponse != null && movieApiResponse.getResults() != null){
                    results =  movieApiResponse.getResults();

                    fillRecyclerView();

                    swipeRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call,
                                  Throwable t) {

            }
        });

    }

    private void fillRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ResultAdapter(this, results);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}