package com.example.univmobile.Common.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.univmobile.Common.LoginSignUp.LoginActivity;
import com.example.univmobile.HelperClasses.OnBoardingAdapter.SliderAdapter;
import com.example.univmobile.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;

    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // status bar akan hilang
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();  //ketika menambah method finish(), setelah activity dijalankan akan dihancurkan dan tidak akan kembali lagi. ketika anda menekan tombol back akan langsung keluar app
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }
    public void getStart(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();  //ketika menambah method finish(), setelah activity dijalankan akan dihancurkan dan tidak akan kembali lagi. ketika anda menekan tombol back akan langsung keluar app
    }

    private void addDots(int position){

        dots = new TextView[4]; //berapa titik
        dotsLayout.removeAllViews();

        for (int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);//kegiatan ini menampilkan text ini di dalam orientasi
            dots[i].setText(Html.fromHtml("&#8226")); // titik dari html
            dots[i].setTextSize(35);  // besar titik

            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0) { //memastikan bawha ada sesuatu jika titik titik panjang lebih besar dari 0
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 1){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 2){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}