package com.example.ncbo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavVH> {

    ArrayList<Article> articles;
    Listener listener;

    //this.marticles= articles;

    public FavAdapter(ArrayList<Article> articles, Listener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavAdapter.FavVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new FavVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.FavVH holder, int position) {
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
    class FavVH extends RecyclerView.ViewHolder {
        TextView txNews,txDesc,txAuthor,txPublishedAt;
        ImageView ivNews;

        ImageButton ibBookmark;
        public FavVH(@NonNull View itemView) {
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
    interface Listener{
        void onItemListener(Article article);
    }
}
