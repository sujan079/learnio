package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.adapter.CategoryCoursesAdapter;
import com.example.learnio.database.LernioDatabase;
import com.example.learnio.model.Courses;
import com.example.learnio.model.Enroll;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {

    private LernioDatabase mDB;
    private RecyclerView rvEnroll;
    private CategoryCoursesAdapter categoryCoursesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDB = LernioDatabase.getINSTANCE(getView().getContext());
        init();
    }

    public void init() {
        rvEnroll = getView().findViewById(R.id.rvCourses);

        categoryCoursesAdapter = new CategoryCoursesAdapter();
        layoutManager = new LinearLayoutManager(getView().getContext());

        categoryCoursesAdapter.setCoursesArrayList(getCourses());

        rvEnroll.setAdapter(categoryCoursesAdapter);
        rvEnroll.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvEnroll);
    }

    public ArrayList<Courses> getCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        for (Enroll enroll : mDB.enrollDao().getAllEnroll()) {
            Courses course = new Courses();
            course.setCourse_url(enroll.getImgUrl());
            course.setLength(enroll.getLength());
            course.setCourseName(enroll.getCourseName());
            course.setId(enroll.getId());

            courses.add(course);

        }
        return courses;
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            ArrayList<Courses> courses = categoryCoursesAdapter.getCoursesArrayList();
            Courses course = courses.get(position);
            Enroll enroll = new Enroll(course.getCourseName(), course.getCourse_url(), course.getLength());
            enroll.setId(course.getId());
            mDB.enrollDao().delete(enroll);

            categoryCoursesAdapter.setCoursesArrayList(getCourses());
            categoryCoursesAdapter.notifyDataSetChanged();

        }
    };
}
