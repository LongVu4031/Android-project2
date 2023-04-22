package com.example.ncbo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ArticleDB {
    private static final String TABLE_NAME = "tbArticles";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ARTICLE_ID = "ArticleID";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_AUTHOR = "Author";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_URL_TO_IMAGE = "UrlToImage";
    private static final String COLUMN_URL = "Url";
    private static final String COMLUMN_PUBLISHED_AT = "publishedAt";
    private SQLiteDatabase db;

    public ArticleDB(Context context) {
        db = context.openOrCreateDatabase("ArticleDatabase", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ARTICLE_ID + " INTEGER NOT NULL, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_AUTHOR + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_URL_TO_IMAGE + " TEXT NOT NULL, " +
                COLUMN_URL + " TEXT NOT NULL, " +
                COMLUMN_PUBLISHED_AT + "TEXT NOT NULL," +
                "UNIQUE (" + COLUMN_ARTICLE_ID + ") ON CONFLICT REPLACE)");
    }
    public void insertArticle(Article article) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTICLE_ID, article.getId());
        values.put(COLUMN_TITLE, article.getTitle());
        values.put(COLUMN_AUTHOR, article.getAuthor());
        values.put(COLUMN_DESCRIPTION, article.getDescription());
        values.put(COLUMN_URL_TO_IMAGE, article.getUrlToImage());
        values.put(COLUMN_URL, article.getUrl());
        values.put(COMLUMN_PUBLISHED_AT, article.getPublishedAt());
        db.insert(TABLE_NAME, null, values);
    }
    public void delete(int articleId) {
        db.delete(TABLE_NAME, COLUMN_ARTICLE_ID + " = ?", new String[]{String.valueOf(articleId)});
    }
    public ArrayList<Article> getAllArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ARTICLE_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            @SuppressLint("Range") String urlToImage = cursor.getString(cursor.getColumnIndex(COLUMN_URL_TO_IMAGE));
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
            @SuppressLint("Range") String publishedAt = cursor.getString(cursor.getColumnIndex(COMLUMN_PUBLISHED_AT));
            Article article = new Article(id, author, title, description, url, urlToImage, publishedAt);
            articles.add(article);
        }
        cursor.close();
        return articles;
    }

    public void close() {
        db.close();
    }
}
