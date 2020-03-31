package com.awak25.listmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.awak25.listmovie.model.ResponseMovie;
import com.awak25.listmovie.model.ResultsItem;
import com.awak25.listmovie.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new GridLayoutManager(this,2));

        String apikey = "0a1f74d199031612733f5a747743e203";
        String languange = "en-US";
        int page = 1;

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        ApiClient.service.getMovie(apikey, languange, page).enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();

                    ResponseMovie movie = response.body();
                    List<ResultsItem> resultsItems = movie.getResults();

                    MovieAdapter adapter = new MovieAdapter(resultsItems, MainActivity.this);
                    adapter.notifyDataSetChanged();

                    rvMovie.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
