package com.rais.haidar.applenewsapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rais.haidar.applenewsapi.ApiRetrofit.ApiService;
import com.rais.haidar.applenewsapi.ApiRetrofit.InstanceRetrofit;
import com.rais.haidar.applenewsapi.Model.ArticlesItem;
import com.rais.haidar.applenewsapi.Model.ResponseNews;
import com.rais.haidar.applenewsapi.Model.Source;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    RecyclerView rvNews;
    public static final String WebUrl = "https://newsapi.org/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                rvNews = findViewById(R.id.rvBerita);
        getData();

    }

    private void getData() {
        ApiService api = InstanceRetrofit.getInstance();
        Call<ResponseNews> call = api.readNews();
        call.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.body().getStatus().equals("ok")) {
                    Toast.makeText(MainActivity.this, ""+response.body().getArticles(), Toast.LENGTH_SHORT).show();
                    Log.e("Data", response.body().getStatus());
                    List<ArticlesItem> isiNews = response.body().getArticles();
                    adapter = new CustomAdapter(MainActivity.this, isiNews);
                    rvNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvNews.setAdapter(adapter);
                } else {
                    Log.e("Gagal", "Ga ngambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
        Context context;
        List<ArticlesItem> isiNews;

        public CustomAdapter(Context context, List<ArticlesItem> isiNews) {

            this.context = context;
            this.isiNews = isiNews;

        }

        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {

            holder.txtTitle.setText(isiNews.get(position).getTitle());
            holder.txtDeskripsi.setText(isiNews.get(position).getDescription());
            holder.txtDate.setText(isiNews.get(position).getPublishedAt());
            holder.txtAuthor.setText(isiNews.get(position).getAuthor());
            Source source = (Source) isiNews.get(position).getSource();

            holder.txtName.setText(source.getName());

            Glide.with(context)
                    .load(isiNews.get(position).getUrlToImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imgNews);


        }

        @Override
        public int getItemCount() {
            return isiNews.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitle, txtAuthor, txtDate, txtDeskripsi,txtName;
            ImageView imgNews;

            public MyViewHolder(View itemView) {
                super(itemView);

                txtAuthor = itemView.findViewById(R.id.txtAuthor);
                txtTitle = itemView.findViewById(R.id.txtTitle);
                txtDate = itemView.findViewById(R.id.txtDate);
                txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
                imgNews = itemView.findViewById(R.id.ImgNews);
                txtName = itemView.findViewById(R.id.txtweb);

            }
        }
    }
}
