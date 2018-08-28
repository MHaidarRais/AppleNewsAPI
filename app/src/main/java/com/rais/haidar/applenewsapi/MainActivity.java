package com.rais.haidar.applenewsapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rais.haidar.applenewsapi.ApiRetrofit.ApiService;
import com.rais.haidar.applenewsapi.ApiRetrofit.InstanceRetrofit;
import com.rais.haidar.applenewsapi.Model.ArticlesItem;
import com.rais.haidar.applenewsapi.Model.ResponseNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        ApiService api = InstanceRetrofit.getInstance();
        Call<ResponseNews> call = api.readNews();
        call.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.body().getStatus() == "ok"){

                    List<ArticlesItem> isiNews = response.body().getArticles();
                    adapter = new CustomAdapter(rvNews,MainActivity.this,isiNews);
                    rvNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvNews.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {

            }
        });
    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
        Context context;
        List<ArticlesItem> isiNews = new ArrayList<>();

        public CustomAdapter(RecyclerView rvArticleNews, Context context, List<ArticlesItem> isiNews) {

            this.context = context;
            this.isiNews = isiNews;

        }

        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.listitem, parent,false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitle,txtAuthor,txtDate,txtDeskripsi;
            public MyViewHolder(View itemView) {
                super(itemView);

                txtAuthor = itemView.findViewById(R.id.txtAuthor);
                txtTitle = itemView.findViewById(R.id.txtTitle);
                txtDate = itemView.findViewById(R.id.txtDate);
                txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
            }
        }
    }
}
