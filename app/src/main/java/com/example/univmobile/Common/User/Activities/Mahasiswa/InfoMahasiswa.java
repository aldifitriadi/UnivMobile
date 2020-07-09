package com.example.univmobile.Common.User.Activities.Mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;

public class InfoMahasiswa extends AppCompatActivity {

    AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button to home
        getSupportActionBar().setTitle("Info Mahasiswa"); // change ttitle on top
        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_info_mahasiswa);

    }

    public void btndataMahasiswa(View view) {
        startActivity(new Intent(getApplicationContext(), DataMahasiswa.class));
    }

    public void btnmataKuliah(View view) {
        startActivity(new Intent(getApplicationContext(), MataKuliah.class));
    }


    public void btndataDosen(View view) {
        startActivity(new Intent(getApplicationContext(), DataDosen.class));
    }

    public void btnpembayaran(View view) {
        startActivity(new Intent(getApplicationContext(), Pembayaran.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back to home
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}