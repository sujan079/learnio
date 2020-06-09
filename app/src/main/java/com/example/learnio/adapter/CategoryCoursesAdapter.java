package com.example.learnio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.learnio.R;
import com.example.learnio.model.Courses;

import java.util.ArrayList;


public class CategoryCoursesAdapter extends RecyclerView.Adapter<CategoryCoursesAdapter.CategoryCourseViewHolder> {

    private ArrayList<Courses> coursesArrayList;

    public void setCoursesArrayList(ArrayList<Courses> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
    }

    public ArrayList<Courses> getCoursesArrayList() {
        return coursesArrayList;
    }

    @NonNull
    @Override
    public CategoryCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_list_item,parent,false);
        return new CategoryCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCourseViewHolder holder, int position) {
        holder.bindData(coursesArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }


    public class CategoryCourseViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivCourseImage;
        private TextView tvCourseTitle,tvCourseDuration;

        public CategoryCourseViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCourseImage=itemView.findViewById(R.id.iv_course_img);
            tvCourseTitle=itemView.findViewById(R.id.tv_course_name);
            tvCourseDuration=itemView.findViewById(R.id.tv_duration);

        }

        public void bindData(Courses courses){

            setIvCourseImage(courses.getCourse_url(),courses.getCourse_logo());
            tvCourseTitle.setText(courses.getCourseName());
            tvCourseDuration.setText(courses.getLength());
        }

        public void setIvCourseImage(String url) {
            Glide
                    .with(ivCourseImage)
                    .load(url)
                    .placeholder(R.drawable.dummy)
                    .into(ivCourseImage);
        }

        public void setIvCourseImage(String url,int drawable) {
            Glide
                    .with(ivCourseImage)
                    .load(url)
                    .placeholder(drawable)
                    .placeholder(R.drawable.placeholder)
                    .into(ivCourseImage);
        }


    }
}
