package com.example.mov001_proyectofinal_ronald_nacho.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.example.mov001_proyectofinal_ronald_nacho.R;


public class fragment1 extends Fragment {

    public String TAG="Taller";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_fragment1, container, false);
        SetupUI(view);

        return view;
    }



    public void SetupUI(final View view){

        AppCompatImageView imPublicar=(AppCompatImageView)view.findViewById(R.id.im1);
        imPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.fragment2);
            }
        });


        AppCompatImageView imConsultar=(AppCompatImageView)view.findViewById(R.id.im2);
        imConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.fragment3);

            }
        });


    }


}
