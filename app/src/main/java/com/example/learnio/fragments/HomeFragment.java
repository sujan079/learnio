package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.learnio.actions.CategoryActionListner;
import com.example.learnio.actions.CoursesActionListner;
import com.example.learnio.adapter.CategoryAdapter;
import com.example.learnio.adapter.CoursesAdapter;
import com.example.learnio.model.Category;
import com.example.learnio.model.Courses;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment implements CoursesActionListner, CategoryActionListner {
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

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

        coursesAdapter = new CoursesAdapter(getContext(), this);


        coursesLayoutManager = new GridLayoutManager(getContext(), 2);


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

    @Override
    public void onCourseItemClick(Courses courses) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host_fragment, new CourseDetailFragment(courses))
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onCategoryItemClicked(String category) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host_fragment, new CategoryCoursesFragment())
                .addToBackStack("")
                .commit();
    }
}
