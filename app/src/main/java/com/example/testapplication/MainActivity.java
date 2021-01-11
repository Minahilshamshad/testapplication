package com.example.testapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static int splach_screem=5000;


    Animation topanimation,bottomanimation;
    ImageView imageView;
    TextView logo,slogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        topanimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imageView=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView);
        slogon=findViewById(R.id.textView2);

        imageView.setAnimation(topanimation);
        logo.setAnimation(bottomanimation);
        slogon.setAnimation(bottomanimation);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                Pair[] pairs=new Pair[2];
                pairs[0] =new Pair<View,String>(imageView,"logo_image");
                pairs[1] =new Pair<View,String>(logo,"logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        },splach_screem);
    }
}