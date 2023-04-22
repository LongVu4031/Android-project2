package com.example.ncbo;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class App extends Application {
    ArticleDB articleDB;
    @Override
    public void onCreate() {
        super.onCreate();
        articleDB = new ArticleDB(this);
       //articleDB.copyDatabase();
      /*  articleDB.createTable();
        if(articleDB.countArticle()==0) {
            try {
                InputStream inputStream = getAssets().open("datanews.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, "UTF-8");
                // Chuyển đổi dữ liệu thành đối tượng JSONArray
                JSONArray jsonArray = new JSONArray(json);

                // Thêm từng bài báo vào database
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Article article = new Article();
                    article.setTitle(jsonObject.getString("title"));
                    article.setAuthor(jsonObject.getString("author"));
                    article.setDescription(jsonObject.getString("description"));
                    article.setUrlToImage(jsonObject.getString("urlToImage"));
                    article.setUrl(jsonObject.getString("url"));
                    article.setPublishedAt(jsonObject.getString("publishedAt"));

                    articleDB.insertArticle(article);
                }


            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }



       */

    }




}
