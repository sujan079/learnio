package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.learnio.R;
import com.example.learnio.adapter.CategoryAdapter;
import com.example.learnio.adapter.CoursesAdapter;
import com.example.learnio.model.Category;
import com.example.learnio.model.Courses;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {
    private RecyclerView rvCategory, rvCourses;
    private CategoryAdapter categoryAdapter;
    private CoursesAdapter coursesAdapter;

    private RecyclerView.LayoutManager categoryLayoutManger, coursesLayoutManager;
    private ImageView ivProfile;

    FirebaseAuth firebaseAuth;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        initCategory();
        initCourses();
        setIvProfile();
    }


    public void initCategory() {
        rvCategory = getView().findViewById(R.id.rv_category);
        categoryAdapter = new CategoryAdapter();
        categoryLayoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        categoryAdapter.setCategories(Category.getCategories());

        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(categoryLayoutManger);
        rvCategory.setHasFixedSize(true);
    }

    public void initCourses() {
        rvCourses = getView().findViewById(R.id.rv_courses);

        coursesAdapter = new CoursesAdapter(getContext());


        coursesLayoutManager = new GridLayoutManager(getContext(), 2);


        rvCourses.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        coursesAdapter.setCourses(Courses.getCourses());

        rvCourses.setAdapter(coursesAdapter);
        rvCourses.setLayoutManager(coursesLayoutManager);
        rvCourses.setHasFixedSize(true);
    }

    public void setIvProfile() {
        ivProfile = getView().findViewById(R.id.iv_profile);
        Glide
                .with(ivProfile)
                .load(firebaseAuth.getCurrentUser().getPhotoUrl())
                .placeholder(R.drawable.dummy)
                .into(ivProfile);
    }
}
