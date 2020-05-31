package com.example.learnio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.actions.CoursesActionListner;
import com.example.learnio.model.Courses;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private ArrayList<Courses> courses;
    private Context context;
    private CoursesActionListner coursesActionListner;


    public CoursesAdapter(Context context, CoursesActionListner coursesActionListner) {
        this.context = context;
        courses = new ArrayList<>();
        this.coursesActionListner = coursesActionListner;
    }

    public void setCourses(ArrayList<Courses> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bindData(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvCourseTitle;
        private TextView mTvCourseLength;
        private ConstraintLayout background;

        public CourseViewHolder(@NonNull View itemView) {

            super(itemView);
            mTvCourseTitle = itemView.findViewById(R.id.tv_course_name);
            mTvCourseLength = itemView.findViewById(R.id.tv_course_time);
            background = itemView.findViewById(R.id.course_container);
            background.setOnClickListener(this);

        }

        public void bindData(Courses courses) {
            mTvCourseTitle.setText(courses.getCourseName());
            mTvCourseLength.setText(courses.getLength());
            background.setBackground(context.getDrawable(courses.getBackground()));

        }

        @Override
        public void onClick(View v) {
            coursesActionListner.onCourseItemClick(courses.get(getAdapterPosition()));
        }
    }
}
