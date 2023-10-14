package com.Mk_Manish.Romantic_love.cattegory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.Mk_Manish.Romantic_love.Array_Adapter_Recycler;
import com.Mk_Manish.Romantic_love.Contact_Model_Row;
import com.Mk_Manish.Romantic_love.R;

import java.util.ArrayList;

public class Dard extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    ArrayList<Contact_Model_Row> arraycontact = new ArrayList<>();
    RecyclerView recyclerView;
    Toolbar toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dard);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dataref =firebaseDatabase.getReference("dard");

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot snap: snapshot.getChildren()) {


                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                        arraycontact.add(new Contact_Model_Row(snap.getValue().toString()));
                        Array_Adapter_Recycler adapter = new Array_Adapter_Recycler(getApplicationContext(),arraycontact);
                        recyclerView.setAdapter(adapter);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}