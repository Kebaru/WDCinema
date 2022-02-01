package com.example.wdcinema_homeversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView headerimg;
    Context context;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/magicians.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        headerimg=findViewById(R.id.header);

        movieCover();
    }

    public void movieCover(){
        Picasso.with(context)
                .load(PHOTO_URL)
                .resize(1080,1080)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(headerimg);
    }
}