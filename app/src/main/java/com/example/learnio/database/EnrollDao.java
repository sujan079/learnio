package com.example.learnio.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learnio.model.Bookmark;
import com.example.learnio.model.Enroll;

import java.util.List;

@Dao
public interface EnrollDao {

    @Insert
    void insert(Enroll enroll);

    @Delete
    void delete(Enroll enroll);

    @Query("SELECT * FROM enroll")
    List<Enroll> getAllEnroll();

}
