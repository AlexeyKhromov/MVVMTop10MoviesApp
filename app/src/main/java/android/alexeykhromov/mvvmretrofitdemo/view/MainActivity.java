package android.alexeykhromov.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.alexeykhromov.mvvmretrofitdemo.viewmodel.MainActivityViewModel;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

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

        mainActivityViewModel.getAllMovieData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultList) {

                results = (ArrayList<Result>) resultList;
                fillRecyclerView();
            }
        });

    }

    private void fillRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ResultAdapter(this, results);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}