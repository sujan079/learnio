package com.example.learnio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnio.adapter.CategoryAdapter;
import com.example.learnio.model.Category;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager categoryLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCategory();
    }

    public void initCategory() {
        rvCategory = findViewById(R.id.rv_category);
        categoryAdapter = new CategoryAdapter();
        categoryLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        categoryAdapter.setCategories(Category.getCategories());

        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(categoryLayoutManger);
        rvCategory.setHasFixedSize(true);
    }
}
