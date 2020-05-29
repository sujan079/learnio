package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.R;
import com.example.learnio.adapter.CategoryAdapter;
import com.example.learnio.model.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager categoryLayoutManger;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCategory();
    }

    public void initCategory() {
        rvCategory = getView().findViewById(R.id.rv_category);
        categoryAdapter = new CategoryAdapter();
        categoryLayoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        categoryAdapter.setCategories(Category.getCategories());

        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(categoryLayoutManger);
        rvCategory.setHasFixedSize(true);
    }
}
