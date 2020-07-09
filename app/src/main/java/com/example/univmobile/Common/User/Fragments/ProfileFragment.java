package com.example.univmobile.Common.User.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.univmobile.Common.User.Activities.EditProfile;
import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    // load Current user profile photo
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mFirebaseDatabase;
    FirebaseDatabase mFirebaseInstance;
    String userID;

    ImageView imageProfile;
    Button resetPassword, changeProfile;
    TextView profileNim, profileFullname, profileUsername, profileProdi, profileEmail, profileTglLahir, profilePhone;

    AppPreferenceManager preferenceManager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
//        return inflater.inflate(R.layout.fragment_profile, container, false);
        preferenceManager = new AppPreferenceManager(getContext());

        // load Current user profile photo
        FirebaseAuth mAuth;
        final FirebaseUser currentUser;

        // ini
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mFirebaseInstance = FirebaseDatabase.getInstance();

        profileNim = v.findViewById(R.id.profileNim);
        profileFullname = v.findViewById(R.id.editNim);
        profileUsername = v.findViewById(R.id.profileUsername);
        profileProdi = v.findViewById(R.id.profileProdi);
        profileEmail = v.findViewById(R.id.profileEmail);
        profileTglLahir = v.findViewById(R.id.profileTglLahir);
        profilePhone = v.findViewById(R.id.profilePhone);
        imageProfile = v.findViewById(R.id.profileImageFragment);
        resetPassword = v.findViewById(R.id.resetPasswordLocal);
        changeProfile = v.findViewById(R.id.changeProfile);

        Glide.with(ProfileFragment.this).load(currentUser.getPhotoUrl()).into(imageProfile);

        userID = mAuth.getCurrentUser().getUid();
        mFirebaseDatabase = mFirebaseInstance.getReference("users").child(userID);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profileNim.setText(snapshot.child("nim").getValue(String.class));
                profileFullname.setText(snapshot.child("fullname").getValue(String.class));
                profileUsername.setText(snapshot.child("name").getValue(String.class));
                profileProdi.setText(snapshot.child("prodi").getValue(String.class));
                profileEmail.setText(snapshot.child("email").getValue(String.class));
                profileTglLahir.setText(snapshot.child("tgllahir").getValue(String.class));
                profilePhone.setText(snapshot.child("phone").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetPassword = new EditText(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter New Password > 6 Characters long.");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String newPassword = resetPassword.getText().toString();
                        currentUser.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Password Reset Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
//                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// membuka dan mengambil gambar di galerry
//                startActivityForResult(openGalleryIntent, 1000);
                Intent i = new Intent(v.getContext(), EditProfile.class); // berpindah ke edit profile dan mengirim data kesana
                i.putExtra("nim", profileNim.getText().toString());
                i.putExtra("fullname", profileFullname.getText().toString());
                i.putExtra("name", profileUsername.getText().toString());
                i.putExtra("prodi", profileProdi.getText().toString());
                i.putExtra("email", profileEmail.getText().toString());
                i.putExtra("tgllahir", profileTglLahir.getText().toString());
                i.putExtra("phone", profilePhone.getText().toString());
                startActivity(i);
            }
        });

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
