package com.Mk_Manish.Romantic_love;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Mk_Manish.Romantic_love.cattegory.Royal;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.Mk_Manish.Romantic_love.cattegory.Attitude;
import com.Mk_Manish.Romantic_love.cattegory.Dard;
import com.Mk_Manish.Romantic_love.cattegory.Dosti;
import com.Mk_Manish.Romantic_love.cattegory.Funny;
import com.Mk_Manish.Romantic_love.cattegory.Good_Morning;
import com.Mk_Manish.Romantic_love.cattegory.Good_Night;
import com.Mk_Manish.Romantic_love.cattegory.Mahakal;
import com.Mk_Manish.Romantic_love.cattegory.Miss_You;
import com.Mk_Manish.Romantic_love.cattegory.Mohabat;
import com.Mk_Manish.Romantic_love.cattegory.Sad;
import com.google.firebase.database.DatabaseReference;


public class Frag_cattegorys extends Fragment {
    private FirebaseAnalytics mFirebaseAnalytics;

    public Frag_cattegorys() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_cattegorys, container, false);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());



        TextView royal = view.findViewById(R.id.royal);
        TextView mahakal = view.findViewById(R.id.mahakal);
        TextView dosti = view.findViewById(R.id.dosti);
        TextView dard = view.findViewById(R.id.dard);
        TextView funny = view.findViewById(R.id.funny);
        TextView attitude = view.findViewById(R.id.attitude);
        TextView sad = view.findViewById(R.id.sad);
        TextView good_morning = view.findViewById(R.id.good_morning);
        TextView good_night = view.findViewById(R.id.good_night);
        TextView mohabat = view.findViewById(R.id.mohabat);
        TextView miss_you = view.findViewById(R.id.miss_you);

        royal.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Royal.class);
            startActivity(i);
        });



        mahakal.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(),Mahakal.class);
            startActivity(i);
        });

        dosti.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(),Dosti.class);
            startActivity(i);
        });

        dard.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(),Dard.class);
            startActivity(i);
        });

        funny.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Funny.class);
            startActivity(i);
        });

        attitude.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Attitude.class);
            startActivity(i);
        });

        sad.setOnClickListener(view1-> {
            Intent i=new Intent(getActivity(), Sad.class);
            startActivity(i);
        });


        mohabat.setOnClickListener(view1-> {
            Intent i=new Intent(getActivity(), Mohabat.class);
            startActivity(i);
        });

        good_morning.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Good_Morning.class);
            startActivity(i);
        });

        good_night.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Good_Night.class);
            startActivity(i);
        });

      miss_you.setOnClickListener(view1 -> {
            Intent i=new Intent(getActivity(), Miss_You.class);
            startActivity(i);
        });


        return view;
    }

}