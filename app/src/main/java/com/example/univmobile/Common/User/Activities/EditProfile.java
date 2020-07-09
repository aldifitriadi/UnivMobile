package com.example.univmobile.Common.User.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    public static final String TAG = "TAG";

    EditText editNim, editFullname, editUsername, editProdi, editEmail, editTglLahir, editPhone;
    Button saveBtn;

    ImageView editImageView;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
//    UserHelperClass usersData;
    DatabaseReference mFirebaseDatabase;
    FirebaseDatabase mFirebaseInstance;
    String userID;

    AppPreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button to home
        getSupportActionBar().setTitle("Edit Profile"); // change ttitle on top
        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String nim = data.getStringExtra("nim");
        String fullname = data.getStringExtra("fullname");
        String name = data.getStringExtra("name");
        String prodi = data.getStringExtra("prodi");
        String email = data.getStringExtra("email");
        final String tgllahir = data.getStringExtra("tgllahir");
        final String phone = data.getStringExtra("phone");

//        // ini
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
////        mFirebaseDatabase = FirebaseStorage.getInstance().getReference();

        editNim = findViewById(R.id.editNim);
        editFullname = findViewById(R.id.editFullName);
        editUsername = findViewById(R.id.editUsername);
        editProdi = findViewById(R.id.editProdi);
        editEmail = findViewById(R.id.editEmail);
        editTglLahir = findViewById(R.id.editTglLahir);
        editPhone = findViewById(R.id.editPhone);
        editImageView = findViewById(R.id.editImageView);

        Glide.with(EditProfile.this).load(currentUser.getPhotoUrl()).into(editImageView);



        editNim.setText(nim);
        editFullname.setText(fullname);
        editUsername.setText(name);
        editEmail.setText(email);
        editProdi.setText(prodi);
        editTglLahir.setText(tgllahir);
        editPhone.setText(phone);

        Log.d(TAG, "onCreate" + name + " " + email + " " + phone);
    }

    public void savebtn(View view) {
//        if (editTglLahir.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty()){
//            Toast.makeText(EditProfile.this, "One or Many Fields are empty", Toast.LENGTH_SHORT).show();
//            return;
//        }
        final String email = editEmail.getText().toString();
        currentUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                userID = mAuth.getCurrentUser().getUid();
                mFirebaseDatabase = mFirebaseInstance.getReference("users").child(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("email", email);
                user.put("fullname", editFullname.getText().toString());
                user.put("nim", editNim.getText().toString());
                user.put("name", editUsername.getText().toString());
                user.put("prodi", editProdi.getText().toString());
                user.put("tgllahir", editTglLahir.getText().toString());
                user.put("phone", editPhone.getText().toString());
                mFirebaseDatabase.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back to home
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}