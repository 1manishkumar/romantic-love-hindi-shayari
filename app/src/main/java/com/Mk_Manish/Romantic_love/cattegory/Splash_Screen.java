package com.Mk_Manish.Romantic_love.cattegory;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mk_Manish.Romantic_love.NavigationDrawer;
import com.Mk_Manish.Romantic_love.R;

public class Splash_Screen extends AppCompatActivity {

    ImageView logo;
    TextView powerby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        logo = findViewById(R.id.logo);
        powerby = findViewById(R.id.powerby);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
        logo.startAnimation(animation);
        powerby.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, NavigationDrawer.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}