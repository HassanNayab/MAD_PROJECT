package com.example.taskmanager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {
    Animation first, second, third;
    View firstview, secondview, thirdview
            , forthview, fifthview, sixthview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        init();

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashScreen.this,
                                MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2500);

    }
    private void init()
    {
        first = AnimationUtils.loadAnimation(this, R.anim.first);
        second = AnimationUtils.loadAnimation(this, R.anim.second);
        third = AnimationUtils.loadAnimation(this, R.anim.third);
        firstview = findViewById(R.id.first);
        secondview = findViewById(R.id.second);
        thirdview = findViewById(R.id.third);
        forthview = findViewById(R.id.fourth);
        fifthview = findViewById(R.id.fifth);
        sixthview = findViewById(R.id.sixth);

        firstview.setAnimation(first);
        secondview.setAnimation(second);
        thirdview.setAnimation(third);
        forthview.setAnimation(first);
        fifthview.setAnimation(second);
        sixthview.setAnimation(third);

    }
}