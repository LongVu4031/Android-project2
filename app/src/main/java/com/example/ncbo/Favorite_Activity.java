package com.example.ncbo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Favorite_Activity extends AppCompatActivity implements FavAdapter.Listener {

    RecyclerView rvFav;

    FavAdapter favAdapter;

    ArrayList<Article> favArticles = new ArrayList<Article>();

       // ArrayList<Article> favArticles2 = new ArrayList<>();


   ArticleDB articleDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFav = findViewById(R.id.rvFav);
        articleDB = new ArticleDB(Favorite_Activity.this);
        favArticles = articleDB.getAllArticles();
        favAdapter = new FavAdapter(favArticles, Favorite_Activity.this);
        rvFav.setAdapter(favAdapter);

        rvFav.setLayoutManager(new LinearLayoutManager(Favorite_Activity.this));
        rvFav.addItemDecoration(new DividerItemDecoration(Favorite_Activity.this, DividerItemDecoration.VERTICAL));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvFav.addItemDecoration(itemDecoration);

     //   favAdapter = new NewsAdapter(favArticles, Favorite_Activity.this);
    }

    @Override
    public void onItemListener(Article article) {

    }
}