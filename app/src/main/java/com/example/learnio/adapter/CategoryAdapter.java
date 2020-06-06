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
import com.example.learnio.actions.CategoryActionListner;
import com.example.learnio.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<Category> categories;
    private CategoryActionListner categoryActionListner;


    public CategoryAdapter(CategoryActionListner actionListner) {
        this.categories = new ArrayList<>();
        this.categoryActionListner = actionListner;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindData(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCategoryName;
        private ImageView mCategoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mCategoryName = itemView.findViewById(R.id.tv_category_name);
            mCategoryImage = itemView.findViewById(R.id.iv_category_img);

            itemView.setOnClickListener(this);
        }

        public void bindData(Category category) {
            mCategoryName.setText(category.getCategoryName());

            setIvCourseImage(category.getImageUrl(), category.getImageRes());
        }

        @Override
        public void onClick(View v) {
            categoryActionListner.onCategoryItemClicked(categories.get(getAdapterPosition()).getCategoryName());
        }

        public void setIvCourseImage(String url, int drawable) {
            Glide
                    .with(mCategoryName)
                    .load(url)
                    .placeholder(drawable)
                    .into(mCategoryImage);
        }

    }
}
