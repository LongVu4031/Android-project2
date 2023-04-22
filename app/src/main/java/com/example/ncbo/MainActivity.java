package com.example.ncbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SearchView.OnQueryTextListener;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.ktx.Firebase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class    MainActivity extends AppCompatActivity implements NewsAdapter.Listener{
    RecyclerView rvNews;
    SearchView searchView;
    NewsAdapter newsAdapter;
    //ArrayList<Article> searchResults = new ArrayList<>();
    // tạo mảng articles
    ArrayList<Article> articles =new ArrayList<Article>();;

    ImageButton ibBookmark;
    FirebaseFirestore firebase;
    ArticleDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //trinh vay du lieu len recycler view
        rvNews = findViewById(R.id.rvNews);

        firebase = FirebaseFirestore.getInstance();
        firebase.collection("Bao").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document : task.getResult() ){
                        String author = document.get("author").toString();
                        String description = document.get("description").toString();
                        String publishedAt = document.get("publishedAt").toString();
                        String title = document.get("title").toString();
                        String url  = document.get("url").toString();
                        String urlToImage = document.get("urlToImage").toString();


                        Article article =new Article(author,description,publishedAt,title,url,urlToImage);
                        articles.add(article);

                }
                 newsAdapter.notifyDataSetChanged();
            }
        });

        //ibBookmark = findViewById(R.id.ibBookmark);
        //search
/*
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newsAdapter.getFilter().filter(newText);
                return true;
            }
        });
        */




        rvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),DetailNewsActivity.class);
                startActivity(intent);
            }
        });

        // tạo mới arraylist
        articles = new ArrayList<>();
        newsAdapter = new NewsAdapter(MainActivity.this, articles);

        rvNews.setAdapter(newsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvNews.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvNews.addItemDecoration(itemDecoration);
    //    updateDataFromJSON();
    }


    private void updateDataFromJSON() {
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("datanews.json");
            String data = convertInputStreamToString(is);
            Gson gson = new GsonBuilder().create();
            BoxBao boxBao = gson.fromJson(data, BoxBao.class);
            //  add tat ca bai baos vao articles
            articles.addAll(boxBao.getArticles());
            // cập nhật dữ liệu trên màn hình chính
          //  newsAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        // dữ liệu vào là string
   private String convertInputStreamToString(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
// StandardCharsets.UTF_8.name() > JDK 7
        return buf.toString("UTF-8");
    }



    @Override
    public void onItemListener(Article article) {
        Intent intent = new Intent(MainActivity.this, DetailNewsActivity.class);
        Bundle b = new Bundle();
        // b.putString("title",article.getTitle().toString());
        //  b.putString("content", article.getContent().toString());
        //  b.putString("image1", article.getUrlToImage().toString());
        //b.putString("image2", article.getUrlToImage().toString());
        b.putString("url", article.getUrl().toString());
        intent.putExtras(b);
        startActivity(intent);


    }



    @Override
    public void onBookmarkListener(Article article) {
        int c = article.getId();
        Toast.makeText(this, "có dữ liệu", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "Số ngẫu nhiên: "+c);
     //   db = new ArticleDB(MainActivity.this);
   //     Article art = db.getByid(c);
      //  Log.d("MainActivity", "Số ngẫu nhiên: " + art.getTitle());

    }
       // db.close();

      //  Log.d("MainActivity", "Số ngẫu nhiên: " + articles.getTitle());


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu );
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav:
                Intent intent = new Intent(MainActivity.this, Favorite_Activity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    //Search
    private void fileList(String text) {
        ArrayList<Article> filteredList = new ArrayList<>();
        for (Article article : articles){
            if(article.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(article);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
        } else{
            newsAdapter.setFilteredList(filteredList);
        }


    }
}





