package com.example.learnio.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.learnio.model.Bookmark;
import com.example.learnio.model.Enroll;

@Database(entities = {Bookmark.class, Enroll.class},version = 1)
public abstract class LernioDatabase extends RoomDatabase {

    private static String DATABASE_NAME="Lernio_db";
    private static LernioDatabase INSTANCE=null;


    public static LernioDatabase getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,LernioDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract BookmarkDao bookmarkDao();
    public abstract EnrollDao enrollDao();


}
