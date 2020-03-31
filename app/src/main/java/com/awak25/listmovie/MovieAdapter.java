package com.awak25.listmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awak25.listmovie.model.ResultsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderMovie> {

    private List<ResultsItem> resultsItems;
    private Context context;

    public MovieAdapter(List<ResultsItem> resultsItems, Context context){
        this.resultsItems = resultsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolderMovie holder, int position) {
        String poster = resultsItems.get(position).getPosterPath();
        holder.tvJudul.setText(resultsItems.get(position).getTitle());
        holder.tvTanggal.setText(resultsItems.get(position).getReleaseDate());

        String urlImage = "https://image.tmdb.org/t/p/w500/";
        Glide.with(context).load(urlImage + poster).into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra("MOVIE", resultsItems.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class ViewHolderMovie extends RecyclerView.ViewHolder {

        private ImageView imgMovie;
        private TextView tvJudul, tvTanggal;

        public ViewHolderMovie(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.img_movie);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
