package com.example.univmobile.Common.User.Activities.Mahasiswa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.univmobile.Common.User.Activities.Home;
import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;

public class SettingsActivity extends AppCompatActivity {

    AppPreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button to home
        getSupportActionBar().setTitle("Setting"); // change ttitle on top
        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_settings);
//        Toolbar toolbar = findViewById(R.id.toolbarSettings);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        CardView cardView = findViewById(R.id.darkModeCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Dark Mode")
                        .setMessage("Enabling/Disabling dark mode requires app UI to restart! Do you want to continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (preferenceManager.getDarkModeState()){
                                    darkMode(false);
                                }else{
                                    darkMode(true);
                                }
                            }
                        }).setNegativeButton("No", null)
                        .create().show();
            }
        });
    }

    private void darkMode(boolean b) {
        preferenceManager.setDarkModeState(b);
        Toast.makeText(this, "Changing dark mode!", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back to home
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}