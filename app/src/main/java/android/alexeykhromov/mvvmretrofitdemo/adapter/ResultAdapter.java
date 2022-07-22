package android.alexeykhromov.mvvmretrofitdemo.adapter;

import android.alexeykhromov.mvvmretrofitdemo.R;
import android.alexeykhromov.mvvmretrofitdemo.databinding.ResuktListItemBinding;
import android.alexeykhromov.mvvmretrofitdemo.model.Result;
import android.alexeykhromov.mvvmretrofitdemo.view.MovieDetailsActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultAdapter
        extends PagedListAdapter<Result,ResultAdapter.ResultViewHolder> {

    private Context context;
   // private ArrayList<Result> results;

    public ResultAdapter(Context context) {
        super(Result.CALLBACK);
        this.context = context;
     //   this.results = results;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ResuktListItemBinding resuktListItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.resukt_list_item, parent, false);

        return new ResultViewHolder(resuktListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result = getItem(position);

        holder.resuktListItemBinding.setResult(result);

    }

//    @Override
//    public int getItemCount() {
//        return results.size();
//    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private ResuktListItemBinding resuktListItemBinding;

        public ResultViewHolder(@NonNull ResuktListItemBinding resulktListItemBinding) {
            super(resulktListItemBinding.getRoot());
            this.resuktListItemBinding = resulktListItemBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Result result = getItem(position);

                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
