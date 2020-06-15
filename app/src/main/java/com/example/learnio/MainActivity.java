package com.example.learnio;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.learnio.fragments.BookmarkFragment;
import com.example.learnio.fragments.CoursesFragment;
import com.example.learnio.fragments.HomeFragment;
import com.example.learnio.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigation();


        if(savedInstanceState==null)
            setFragment(new HomeFragment());
    }


    public void initBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.action_bookmark:
                        setFragment(new BookmarkFragment());
                        break;
                    case R.id.action_courses:
                        setFragment(new CoursesFragment());
                        break;
                    case R.id.action_profile:
                        setFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }


    public void setFragment(Fragment hostFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host_fragment, hostFragment)
                .commit();
    }

}
