package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.adapter.CourseContentAdapter;
import com.example.learnio.model.Courses;


public class CourseDetailFragment extends Fragment {

    private Courses courses;

    private TextView tvCourseName, tvCourseRating, tvCourseNumberOfUsers, tvCourseContents, tvEnrollBtn;
    private RecyclerView rvCourseContent;
    private RecyclerView.LayoutManager layoutManager;
    private CourseContentAdapter courseContentAdapter;
    private ConstraintLayout courseBackground;

    public CourseDetailFragment(Courses courses) {
        this.courses = courses;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        populateData();
        populateCourseContent();


    }


    public void init() {
        tvCourseContents = getView().findViewById(R.id.tv_number_course_content);
        tvCourseName = getView().findViewById(R.id.tv_course_name);
        tvCourseRating = getView().findViewById(R.id.tv_rating);
        tvCourseNumberOfUsers = getView().findViewById(R.id.tv_number_users);
        tvEnrollBtn = getView().findViewById(R.id.btn_enroll);
        courseBackground=getView().findViewById(R.id.iv_course_bg);

        rvCourseContent = getView().findViewById(R.id.rv_course_content);


    }

    public void populateCourseContent() {

        courseContentAdapter = new CourseContentAdapter();
        layoutManager = new LinearLayoutManager(getContext());

        courseContentAdapter.setCourseContents(courses.getCourseContents());
        
        rvCourseContent.setAdapter(courseContentAdapter);
        rvCourseContent.setLayoutManager(layoutManager);
        rvCourseContent.setHasFixedSize(true);
    }

    public void populateData() {

        tvCourseName.setText(courses.getCourseName());
        tvCourseNumberOfUsers.setText(String.valueOf(courses.getUsers()));
        tvCourseRating.setText(String.valueOf(courses.getRating()));
        tvCourseContents.setText(String.valueOf(courses.getCourseContents().size()));

        courseBackground.setBackground(getActivity().getDrawable(courses.getBackground()));


    }


}
