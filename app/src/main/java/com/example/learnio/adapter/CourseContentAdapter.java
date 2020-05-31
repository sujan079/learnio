package com.example.learnio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.model.CourseContent;

import java.util.ArrayList;

public class CourseContentAdapter extends RecyclerView.Adapter<CourseContentAdapter.CourseContentViewHolder> {


    private ArrayList<CourseContent> courseContents;

    public void setCourseContents(ArrayList<CourseContent> courseContents) {
        this.courseContents = courseContents;
    }

    @NonNull
    @Override
    public CourseContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_content_item, parent, false);

        return new CourseContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseContentViewHolder holder, int position) {
        holder.bindData(courseContents.get(position));
    }

    @Override
    public int getItemCount() {
        return courseContents.size();
    }

    class CourseContentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvLength;

        CourseContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLength = itemView.findViewById(R.id.tv_course_time);
            tvTitle = itemView.findViewById(R.id.tv_course_name);
        }

        public void bindData(CourseContent courseContent) {
            tvTitle.setText(courseContent.getTitle());
            tvLength.setText(courseContent.getDuration());
        }

    }
}
