package com.example.instaclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView bnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView toolbar_Activity=toolbar.findViewById(R.id.toolbar_activity);

        toolbar_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Notifications.class);
                startActivity(intent);
            }
        });


        bnView=findViewById(R.id.bnView);


        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.home)
                {
                    loadFragment(new HomeFragment(),true);
                } else if (id==R.id.search) {
                    loadFragment(new SearchFragment(),false);
                }
                else if (id==R.id.add)
                {
                    loadFragment(new AddFragment(),false);
                }
                else if (id==R.id.reels)
                {
                    loadFragment(new ReelsFragment(),false);
                }
                else{
                    loadFragment(new UserFragment(),false);
                }
                return true;
            }
        });

        bnView.setSelectedItemId(R.id.home);

    }

    public void loadFragment(Fragment frag,boolean flag)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(flag==true)
        {
            ft.add(R.id.container,frag);
        }
        else{
            ft.replace(R.id.container,frag);
        }
        ft.commit();
    }
}