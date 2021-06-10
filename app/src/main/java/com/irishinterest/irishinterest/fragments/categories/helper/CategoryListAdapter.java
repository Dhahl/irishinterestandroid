package com.irishinterest.irishinterest.fragments.categories.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.BooksActivity;
import com.irishinterest.irishinterest.fragments.categories.CategoryFragment;
import com.irishinterest.irishinterest.model.categories.Category;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Category> categories;
    private CategoryFragment categoryFragment;

    public CategoryListAdapter(ArrayList<Category> categories, Context context, CategoryFragment categoryFragment) {
        this.categories = categories;
        this.context = context;
        this.categoryFragment = categoryFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }

        private TextView categoryTextView;

        public void bindView(Category category){
            categoryTextView = itemView.findViewById(R.id.categoryText);
            categoryTextView.setText(category.getName().replace("'", ""));
            categoryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BooksActivity.class);
                    intent.putExtra("type", "category");
                    intent.putExtra("categoryName", category.getName().replace("'", ""));
                    intent.putExtra("categoryId", String.valueOf(category.getId()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bindView(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setBooks(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
