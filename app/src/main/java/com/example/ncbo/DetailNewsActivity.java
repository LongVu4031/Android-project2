package com.example.ncbo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailNewsActivity extends AppCompatActivity {
    //TextView title;
   // TextView content;
    //ImageView ivAnh1;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        //title = findViewById(R.id.title);
       // content = findViewById(R.id.content);
       // ivAnh1 = findViewById(R.id.ivAnh1);
        // ivAnh2 = findViewById(R.id.ivAnh2);
        webView = (WebView) this.findViewById(R.id.webView);
        //  scrollView=findViewById(R.id.scrollView);

        Bundle b =getIntent().getExtras();
       // title.setText(b.getString("title"));
      //  content.setText(b.getString("content"));
        //String linkimg1= b.getString("image1").toString();
        //String linkimg2 = b.getString("image2").toString();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(b.getString("url"));

     //   Picasso.get().load(linkimg1).into(ivAnh1);
    }
}