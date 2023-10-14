package com.Mk_Manish.Romantic_love.cattegory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.Mk_Manish.Romantic_love.Array_Adapter_Recycler;
import com.Mk_Manish.Romantic_love.Contact_Model_Row;
import com.Mk_Manish.Romantic_love.R;

import java.util.ArrayList;

public class Sad extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    ArrayList<Contact_Model_Row> arraycontact = new ArrayList<>();
    RecyclerView recyclerView;
    Toolbar toolbar;
    InterstitialAd minterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sad);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sad");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dataref =firebaseDatabase.getReference("sad");

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


        InterstitialAd.load(this, "ca-app-pub-5709029477840111/5993517224", adRequest, new InterstitialAdLoadCallback() {

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("Error", loadAdError.toString());
            }


            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);

                minterstitialAd = interstitialAd;

                minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        minterstitialAd = null;
                    }
                });

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (minterstitialAd != null)
                    minterstitialAd.show(Sad.this);
                else
                    Log.e("AdPending", "Ad is not redy yet!");
            }
        }, 5000);

    }
}