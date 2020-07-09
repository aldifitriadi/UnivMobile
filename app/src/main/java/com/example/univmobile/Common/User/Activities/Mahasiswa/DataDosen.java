package com.example.univmobile.Common.User.Activities.Mahasiswa;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;

public class DataDosen extends AppCompatActivity {

    AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button to home
        getSupportActionBar().setTitle("Data Dosen"); // change ttitle on top
        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_data_dosen);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back to home
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}