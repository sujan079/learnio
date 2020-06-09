package com.example.learnio.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmark")
public class Bookmark {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String imgUrl;

    private String courseName;

    private String length;


    public Bookmark(String courseName,String imgUrl, String length) {
        this.imgUrl = imgUrl;
        this.courseName = courseName;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
}
