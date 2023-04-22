package com.example.ncbo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVH> /*implements Filterable */ {


    ArrayList<Article> articles;
    //ArrayList<Article> marticles;
    Listener listener;
        // khai báo constructor
    public NewsAdapter(Listener listener,ArrayList<Article> articles ){

        this.listener = listener;

        this.articles = articles;
        //this.marticles= articles;
    }

    //search
    public void setFilteredList (ArrayList<Article> filteredList){
        this.articles= filteredList;
        notifyDataSetChanged();
    }



        // tạo layout , lấy layout tù items
    @NonNull
    @Override
    public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent,false);
        return new NewsVH(view);
    }
            // lấy dữ liệu
    @Override
    public void onBindViewHolder(@NonNull NewsVH holder, int position) {
        Article model = articles.get(position);
        String urlToImage = model.getUrlToImage();
        ImageView imageView = holder.ivNews;
        if (urlToImage != null && !urlToImage.isEmpty()) {
            Picasso.get().load(urlToImage).into(imageView);
        } else {
            // Nếu không có URL, hiển thị một ảnh mặc định
            imageView.setImageResource(R.drawable.baseline_disabled_by_default_24);
        }
        holder.txNews.setText(model.getTitle());
        holder.txAuthor.setText(model.getAuthor());
        holder.txDesc.setText(model.getDescription());
        holder.txPublishedAt.setText(model.getPublishedAt());
        // Picasso.get().load(model.getUrlToImage()).into(holder.ivNews);
        holder.ibBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDB db = new ArticleDB(imageView.getContext());
                db.insertArticle(model);
                Toast.makeText(holder.itemView.getContext(),"Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemListener(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



// hiển thị dyngf để khai báo dữ liệu
    class NewsVH extends RecyclerView.ViewHolder{
        TextView txNews,txDesc,txAuthor,txPublishedAt;
        ImageView ivNews;

        ImageButton ibBookmark;

        public NewsVH(@NonNull View itemView) {
            super(itemView);
            txNews = itemView.findViewById(R.id.title);
            txDesc = itemView.findViewById(R.id.desc);
            //   txSource = itemView.findViewById(R.id.source);
            txAuthor = itemView.findViewById(R.id.author);
            txPublishedAt = itemView.findViewById(R.id.publishedAt);
            ivNews = itemView.findViewById(R.id.ivNews);
            ibBookmark = itemView.findViewById(R.id.ibBookmark);
        }
    }
    //tạo sự kiện
    interface Listener{
        void onItemListener(Article article);
        void onBookmarkListener(Article article);
    }
/*
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    articles =marticles;
                } else {
                    ArrayList<Article> list = new ArrayList<>();
                    for (Article article : marticles){
                        if(article.getTitle().toLowerCase().contains(strSearch.toLowerCase()));
                        {
                            list.add(article);
                        }
                    }
                    articles = list;
                }
                    FilterResults filterResults = new FilterResults();
                filterResults.values = articles;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    articles =(ArrayList<Article>) results.values;
                    notifyDataSetChanged();
            }
        };
        */

    }







