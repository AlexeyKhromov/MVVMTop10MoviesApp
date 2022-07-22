package android.alexeykhromov.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;


import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MovieDetailsActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movieData")){

            result = intent.getParcelableExtra("movieData");
            Toast.makeText(this, result.getOriginalTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}