package com.irishinterest.irishinterest.fragments.categories.helper

import android.content.Context
import com.irishinterest.irishinterest.fragments.categories.CategoryFragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.irishinterest.irishinterest.R
import android.content.Intent
import com.irishinterest.irishinterest.BooksActivity
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.irishinterest.irishinterest.model.categories.Category
import java.util.ArrayList

class CategoryListAdapter(
    private var categories: ArrayList<Category>,
    private val context: Context,
    private val categoryFragment: CategoryFragment
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    inner class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
        fun bindView(category: Category) {
            val categoryTextView: TextView = itemView.findViewById(R.id.categoryText)
            categoryTextView.setText(category.name!!.replace("'", ""))
            categoryTextView.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, BooksActivity::class.java)
                intent.putExtra("type", "category")
                intent.putExtra("categoryName", category.name!!.replace("'", ""))
                intent.putExtra("categoryId", category.id.toString())
                context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bindView(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setBooks(categories: ArrayList<Category>) {
        this.categories = categories
    }
}