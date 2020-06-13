package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.adapter.CategoryCoursesAdapter;
import com.example.learnio.model.Courses;

import java.util.ArrayList;


public class CategoryCoursesFragment extends Fragment {

    private TextView tvCourseCategory;
    private String category;
    private RecyclerView rvCategoryCourse;
    private CategoryCoursesAdapter categoryCoursesAdapter;
    private RecyclerView.LayoutManager categoryLayoutManager;

    public CategoryCoursesFragment(String category) {
        this.category = category;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

    }

    public void init() {
        rvCategoryCourse = getView().findViewById(R.id.rv_category_courses);

        tvCourseCategory = getView().findViewById(R.id.tv_course_category);

        categoryCoursesAdapter = new CategoryCoursesAdapter();
        categoryLayoutManager = new LinearLayoutManager(getContext());

        categoryCoursesAdapter.setCoursesArrayList(getCourseCategory());

        rvCategoryCourse.setAdapter(categoryCoursesAdapter);
        rvCategoryCourse.setLayoutManager(categoryLayoutManager);
        rvCategoryCourse.setHasFixedSize(true);

        tvCourseCategory.setText(category+" Courses");
    }

    public ArrayList<Courses> getCourseCategory() {
        ArrayList<Courses> courses = new ArrayList<>();

        for (Courses c : Courses.getCourses()) {
            if (c.getCategory().equals(category)) {
                courses.add(c);
            }
        }
        return courses;
    }
}
