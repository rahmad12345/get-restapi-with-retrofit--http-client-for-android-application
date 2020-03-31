package com.awak25.listmovie;

import android.os.Bundle;

import com.awak25.listmovie.model.ResultsItem;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imgDetail = findViewById(R.id.img_detail);
        TextView tvTanggal = findViewById(R.id.tv_tanggal);
        TextView tvContent = findViewById(R.id.tv_content_detail);

        ResultsItem item = getIntent().getParcelableExtra("MOVIE");
        String urlImage = "https://image.tmdb.org/t/p/w500";
        Glide.with(this).load(urlImage + item.getBackdropPath()).into(imgDetail);

        tvTanggal.setText(item.getReleaseDate());
        tvContent.setText(item.getOverview());
        getSupportActionBar().setTitle(item.getTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
