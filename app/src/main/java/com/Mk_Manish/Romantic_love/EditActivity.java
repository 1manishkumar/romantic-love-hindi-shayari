package com.Mk_Manish.Romantic_love;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import yuku.ambilwarna.AmbilWarnaDialog;

public class EditActivity extends AppCompatActivity {

    Button tcolor, background, gradiants, images, status, share, save, gallery;
    RelativeLayout layout;
    EditText shayari, name;
    Toolbar toolbar;
    CircleImageView profile_image;
    int textDefault;
    int backDefault;
    private AdView mAdView;
    private RewardedAd rewardedAd;

    int curent_gradiant = 0;
    int[] gradiant;
    int curent_image = 0;
    int[] image;
    private Integer[] imageIDs = {
            R.drawable.editimage_b
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        shayari = findViewById(R.id.shayri);
        name = findViewById(R.id.name);
        save = findViewById(R.id.save);
        gallery = findViewById(R.id.gallery);
        tcolor = findViewById(R.id.text_color);
        background = findViewById(R.id.background);
        images = findViewById(R.id.images);
        status = findViewById(R.id.status);
        share = findViewById(R.id.share);
        layout = findViewById(R.id.linear);
        profile_image = findViewById(R.id.profile_image);
        gradiants = findViewById(R.id.gradiant);
        textDefault = ContextCompat.getColor(EditActivity.this, R.color.black);
        backDefault = ContextCompat.getColor(EditActivity.this, R.color.black);
        shayari.setText(getIntent().getStringExtra("shayari"));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Shayari");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(EditActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        rewardeadload();



        //this is a text color changer button

        tcolor.setOnClickListener(view -> {
            AmbilWarnaDialog colorpick = new AmbilWarnaDialog(EditActivity.this, textDefault, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    textDefault = color;
                    shayari.setTextColor(textDefault);
                    name.setTextColor(textDefault);
                }
            });
            colorpick.show();
        });


        //this is a background color changer button

        background.setOnClickListener(view -> {
            AmbilWarnaDialog backcolor = new AmbilWarnaDialog(EditActivity.this, backDefault, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {

                }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    backDefault = color;
                    layout.setBackgroundColor(backDefault);
                }
            });

            backcolor.show();
        });


        gradiants.setOnClickListener(view -> {
            int nameofgradiant = 7;
            gradiant = new int[nameofgradiant];
            gradiant[0] = R.drawable.gradiant_a;
            gradiant[1] = R.drawable.gradiant_b;
            gradiant[2] = R.drawable.gradient_c;
            gradiant[3] = R.drawable.gradient_d;
            gradiant[4] = R.drawable.gradiant_e;
            gradiant[5] = R.drawable.gradiant_f;
            gradiant[6] = R.drawable.gradiant_g;
            layout.setBackgroundResource(gradiant[curent_gradiant]);
            ++curent_gradiant;
            if (curent_gradiant == gradiant.length - 1)
                curent_gradiant = 0;
        });

        images.setOnClickListener(view -> {
            int nameofimage = 6;
            image = new int[nameofimage];
            image[0] = R.drawable.editimage_a;
            image[1] = R.drawable.editimage_b;
            image[2] = R.drawable.editimage_c;
            image[3] = R.drawable.editimage_e;
            image[4] = R.drawable.editimage_f;
            image[5] = R.drawable.editimage_g;
            layout.setBackgroundResource(image[curent_image]);
            ++curent_image;
            if (curent_image == image.length - 1)
                curent_image = 0;

        });


        share.setOnClickListener(view -> {



            if (rewardedAd != null) {
                Activity activityContext = EditActivity.this;
                rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        // Handle the reward.
                    }
                });

                rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        // Called when a click is recorded for an ad.
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        rewardedAd = null;
                        sharelayout();
                        rewardeadload();

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when ad fails to show.
                        rewardedAd = null;
                        sharelayout();
                    }

                    @Override
                    public void onAdImpression() {
                        // Called when an impression is recorded for an ad.
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                    }
                });
            } else {
                sharelayout();
            }



        });


        status.setOnClickListener(view -> {

            layout.buildDrawingCache();
            layout.setDrawingCacheEnabled(true);
            Bitmap bidmapPath = Bitmap.createBitmap(layout.getDrawingCache(true));
            String bidmaps = MediaStore.Images.Media.insertImage(getContentResolver(), bidmapPath, "title", null);
            Uri uri = Uri.parse(bidmaps);
            layout.setDrawingCacheEnabled(false);


            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage("com.whatsapp");
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, "share image"));
        });


        save.setOnClickListener(view -> {
            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            Bitmap bitmap = layout.getDrawingCache();

            String bidmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
            Uri uri = Uri.parse(bidmapPath);

            Toast.makeText(EditActivity.this, "Save", Toast.LENGTH_SHORT).show();

        });





        profile_image.setOnClickListener(view -> {

            ImagePicker.Companion.with(this)
                    .crop()
                    .cropSquare()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();

        });

        gallery.setOnClickListener(view -> {

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, 100);
            gallery.putExtra("gallery", gallery);
        });


    }

    private void sharelayout() {

        layout.buildDrawingCache();
        layout.setDrawingCacheEnabled(true);
        Bitmap bidmapPath = Bitmap.createBitmap(layout.getDrawingCache(true));
        String bidmaps = MediaStore.Images.Media.insertImage(getContentResolver(), bidmapPath, "title", null);
        Uri uri = Uri.parse(bidmaps);
        layout.setDrawingCacheEnabled(false);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.mkcdeveloper.Romantic_love");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "share image"));

    }

    private void rewardeadload() {


        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(EditActivity.this, "ca-app-pub-5709029477840111/1783012738",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri uris = data.getData();
        profile_image.setImageURI(uris);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                // Load the image from the URI
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                // Convert the Bitmap to a Drawable
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);

                // Set the Drawable as the background of your LinearLayout
                RelativeLayout layout = findViewById(R.id.linear);
                layout.setBackground(drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
