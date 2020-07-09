package com.example.univmobile.Common.User.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.univmobile.Common.User.Activities.Mahasiswa.FasilitasActivity;
import com.example.univmobile.Common.User.Activities.Mahasiswa.InfoFakultas;
import com.example.univmobile.Common.User.Activities.Mahasiswa.InfoMahasiswa;
import com.example.univmobile.Common.User.Activities.Mahasiswa.TentangKampus;
import com.example.univmobile.HelperClasses.AppPreferenceManager;
import com.example.univmobile.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MahasiswaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MahasiswaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MahasiswaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CardView infoMahasiswa, infoFakultas, fasilitas, tentangKampus;
    TextView web;

    AppPreferenceManager preferenceManager;


    public MahasiswaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MahasiswaFragment newInstance(String param1, String param2) {
        MahasiswaFragment fragment = new MahasiswaFragment();
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
        View v = inflater.inflate(R.layout.fragment_mahasiswa, container, false);
//        return inflater.inflate(R.layout.fragment_profile, container, false);
        preferenceManager = new AppPreferenceManager(getContext());

        infoMahasiswa = v.findViewById(R.id.infoMahasiswa);
        infoFakultas = v.findViewById(R.id.infoFakultas);
        fasilitas = v.findViewById(R.id.fasilitas);
        tentangKampus = v.findViewById(R.id.tentangKampus);
        web = v.findViewById(R.id.web);

        infoMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), InfoMahasiswa.class);
                startActivity(i);
            }
        });

        infoFakultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), InfoFakultas.class);
                startActivity(i);
            }
        });

        fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FasilitasActivity.class);
                startActivity(i);
            }
        });

        tentangKampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TentangKampus.class);
                startActivity(i);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent (Intent.ACTION_VIEW, Uri.parse("http://fakultaskomputerumht.com"));
                startActivity(webIntent);
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
