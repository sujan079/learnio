package com.example.learnio.model;

import com.example.learnio.R;

import java.util.ArrayList;

public class Courses {
    private String courseName;
    private String length;
    private int background;
    private int users;
    private double rating;

    private ArrayList<CourseContent> courseContents;



    public Courses(String courseName, String length) {
        this.courseName = courseName;
        this.length = length;

    }

    public Courses(String courseName, String length, int background) {
        this.courseName = courseName;
        this.length = length;
        this.background = background;
    }

    public Courses(String courseName, String length, int background, int users, double rating, ArrayList<CourseContent> courseContents) {
        this.courseName = courseName;
        this.length = length;
        this.background = background;
        this.users = users;
        this.rating = rating;
        this.courseContents = courseContents;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<CourseContent> getCourseContents() {
        return courseContents;
    }

    public void setCourseContents(ArrayList<CourseContent> courseContents) {
        this.courseContents = courseContents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public static ArrayList<Courses> getCourses() {
        return new ArrayList<Courses>() {{
            add(new Courses("Introduction to Computer Science",
                    "4 hrs",
                    R.drawable.blue
            ));
            add(new Courses("Introduction to Graphic Design", "2 hrs", R.drawable.pink));
            add(new Courses("Game Development", "5 hr", R.drawable.green));
            add(new Courses("Hello", "6 hrs", R.drawable.black));
        }};
    }
}
