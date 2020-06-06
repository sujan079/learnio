package com.example.learnio.model;

import com.example.learnio.R;

import java.util.ArrayList;


public class Courses {
    private String courseName;
    private String length;
    private int background;
    private int users;
    private double rating;
    private String category;
    private int course_logo;

    private String course_url;



    public int getCourse_logo() {
        return course_logo;
    }

    public void setCourse_logo(int course_logo) {
        this.course_logo = course_logo;
    }

    private ArrayList<CourseContent> courseContents;



    public Courses(String courseName, String length) {
        this.courseName = courseName;
        this.length = length;

    }

    public String getCourse_url() {
        return course_url;
    }

    public void setCourse_url(String course_url) {
        this.course_url = course_url;
    }

    public Courses(String courseName, String length, int background, String category, String course_url) {
        this.courseName = courseName;
        this.length = length;
        this.background = background;
        this.category = category;
        this.course_url = course_url;
    }

    public Courses(String courseName, String length, int background, String category, int course_logo) {
        this.courseName = courseName;
        this.length = length;
        this.background = background;
        this.courseContents = new ArrayList<>();
        this.category = category;
        this.course_logo=course_logo;
    }


    public Courses(String courseName, String length, int background, int users, double rating, String category, ArrayList<CourseContent> courseContents,int course_logo) {
        this.courseName = courseName;
        this.length = length;
        this.background = background;
        this.users = users;
        this.rating = rating;
        this.category = category;
        this.courseContents = courseContents;
        this.course_logo=course_logo;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                    R.drawable.blue,
                    18,
                    4.8,
                    "Coding",
                    new ArrayList<CourseContent>(){{
                        add(new CourseContent("Welcome to the Course","3.35min"));
                        add(new CourseContent("Introduction to Android","5.35min"));
                        add(new CourseContent("Recycle View","60min"));
                        add(new CourseContent("Networking","120min"));
                        add(new CourseContent("UI","120min"));
                    }}
                    ,R.drawable.android));
            add(new Courses("Introduction to Graphic Design", "2 hrs", R.drawable.pink, "Coding",R.drawable.angularjs));
            add(new Courses("Game Development", "5 hr", R.drawable.green, "Photography",R.drawable.android));
            add(new Courses("Database", "12 hrs", R.drawable.black, "Coding",R.drawable.angularjs));
            add(new Courses("Database", "12 hrs", R.drawable.black, "Coding","https://banner2.kisspng.com/20180717/jsr/kisspng-computer-icons-oracle-database-clip-art-db-logo-5b4d9ea94e9296.6349990715318135453218.jpg"));

        }};
    }
}
