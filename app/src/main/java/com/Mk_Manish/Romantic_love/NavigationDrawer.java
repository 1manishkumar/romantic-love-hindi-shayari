package com.Mk_Manish.Romantic_love;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationview);
        toolbar=findViewById(R.id.toolbar);

        MobileAds.initialize(NavigationDrawer.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        loadfragment(new Frag_cattegorys());

        //set toolbar to action bar
        setSupportActionBar(toolbar);

        //this is actionbar close and open
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open_Drawer,R.string.Close_Drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id==R.id.home){
                    loadfragment(new Frag_cattegorys());

                }else if(id==R.id.share){

                    Intent myintent = new Intent(Intent.ACTION_SEND);
                    myintent.setType("text/plain");
                    String sharebody="https://play.google.com/store/apps/details?id=com.mkcdeveloper.Romantic_love";
                    String shareSub="subject hare";
                    myintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                    myintent.putExtra(Intent.EXTRA_TEXT,sharebody);
                    startActivity(Intent.createChooser(myintent,"share using"));
                    Toast.makeText(NavigationDrawer.this, "share", Toast.LENGTH_SHORT).show();

                }else if(id==R.id.rating){
                    Intent intent =new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.mkcdeveloper.Romantic_love"));
                    startActivity(intent);
                    Toast.makeText(NavigationDrawer.this, "Rate this App", Toast.LENGTH_SHORT).show();



                }else if(id==R.id.more_apps){
                    Intent intent =new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=MK+developer"));
                    startActivity(intent);
                    Toast.makeText(NavigationDrawer.this, "More Apps", Toast.LENGTH_SHORT).show();

                } else{
                    Intent intent =new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://manishprivacypolicy1.blogspot.com/2023/05/romantic-love-privacy-policy.html?m=1"));
                    startActivity(intent);
                    Toast.makeText(NavigationDrawer.this, "Privcy Policy", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    private void loadfragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
       ft.add(R.id.container,fragment);
       ft.commit();

    }
}