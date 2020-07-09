package com.example.univmobile.Common.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.univmobile.Common.LoginSignUp.LoginActivity;
import com.example.univmobile.R;

public class SplashScreen extends AppCompatActivity {

    //variabel yang akan menampung waktu animasi yg merupakan jenis static private
    private static int SPLASH_TIMER = 5000;

    //Variables // Java menggunakan Capital bukan garis bawah / style CamelCase
    ImageView backgroundImage;
    TextView poweredByLine;

    // Animations
    Animation fadeAnim, bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // status bar akan hilang
        setContentView(R.layout.activity_splash_screen);

        //Hooks // menghubungkan elemen desain ke java
        backgroundImage = findViewById(R.id.background_image); // resource.id. ..
        poweredByLine = findViewById(R.id.powered_by_line);

        //Animations
        fadeAnim = AnimationUtils.loadAnimation(this, R.anim.fade);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set Animations on elements
        backgroundImage.setAnimation(fadeAnim);
        poweredByLine.setAnimation(bottomAnim);

        //fungsi handler yang merupakan posthandler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("fisrtTime", true);

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("fisrtTime", false);
                    editor.commit(); // itu hanya terjadi untuk melakukan sebuah perubahan

                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    //ketika back akan kembali ke splashscreen karena activity tidak hancur
                    //ketika menambah method finish(), setelah activity dijalankan akan dihancurkan dan tidak akan kembali lagi. ketika anda menekan tombol back akan langsung keluar app
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    //ketika back akan kembali ke splashscreen karena activity tidak hancur
                    //ketika menambah method finish(), setelah activity dijalankan akan dihancurkan dan tidak akan kembali lagi. ketika anda menekan tombol back akan langsung keluar app
                    finish();
                }


            }
        },SPLASH_TIMER);
    }
}