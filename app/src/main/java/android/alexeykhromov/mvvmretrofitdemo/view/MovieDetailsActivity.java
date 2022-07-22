package android.alexeykhromov.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;


import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private Result result;
    private ImageView posterImageView;
    private  String posterPath;
    private TextView titleTextVIew;
    private TextView voteCountTextView;
    private TextView overviewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        posterImageView = findViewById(R.id.posterImageView);
        titleTextVIew = findViewById(R.id.titleTextView);
        voteCountTextView = findViewById(R.id.voteCountTextView);
        overviewTextView = findViewById(R.id.overviewTextView);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movieData")){

            result = intent.getParcelableExtra("movieData");
            Toast.makeText(this, result.getOriginalTitle(), Toast.LENGTH_SHORT).show();

            posterPath = result.getPosterPath();
            String imagePath = "https://image.tmdb.org/t/p/w500/" + posterPath;
            Glide.with(this).load(imagePath).placeholder(R.drawable.circle_progress).into(posterImageView);

            titleTextVIew.setText(result.getTitle());
            voteCountTextView.setText(Integer.toString(result.getVoteCount()));
            overviewTextView.setText(result.getOverview());
        }
    }
}