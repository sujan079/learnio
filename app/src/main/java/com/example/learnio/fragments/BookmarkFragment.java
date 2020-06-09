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
import com.example.learnio.model.Bookmark;
import com.example.learnio.model.Courses;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment {

    private LernioDatabase mDB;
    private RecyclerView rvBookmark;
    private CategoryCoursesAdapter categoryCoursesAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDB = LernioDatabase.getINSTANCE(view.getContext());
        init();

    }

    public void init() {
        rvBookmark = getView().findViewById(R.id.rvBookmark);

        categoryCoursesAdapter = new CategoryCoursesAdapter();
        layoutManager = new LinearLayoutManager(getView().getContext());

        categoryCoursesAdapter.setCoursesArrayList(getCourses());

        rvBookmark.setAdapter(categoryCoursesAdapter);
        rvBookmark.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvBookmark);
    }

    public ArrayList<Courses> getCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        for (Bookmark bookmark : mDB.bookmarkDao().getAllBookmark()) {
            Courses course = new Courses();
            course.setCourse_url(bookmark.getImgUrl());
            course.setLength(bookmark.getLength());
            course.setCourseName(bookmark.getCourseName());
            course.setId(bookmark.getId());

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
            Bookmark bookmark = new Bookmark(course.getCourseName(), course.getCourse_url(), course.getLength());
            bookmark.setId(course.getId());
            mDB.bookmarkDao().delete(bookmark);

            categoryCoursesAdapter.setCoursesArrayList(getCourses());
            categoryCoursesAdapter.notifyDataSetChanged();

        }
    };
}
