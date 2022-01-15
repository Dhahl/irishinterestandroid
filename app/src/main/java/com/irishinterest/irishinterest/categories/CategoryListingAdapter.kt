package com.irishinterest.irishinterest.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.webservice.Category
import com.irishinterest.irishinterest.R

class CategoryListingAdapter(private var categories : List<Category>,
                         private var inflater: LayoutInflater,
                         private val onCategoryListener: (Category) -> Unit) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(inflater.inflate(R.layout.category_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.update(name = category.displayName)
        holder.itemView.setOnClickListener(onClicked(position))
    }

    private fun onClicked(position: Int): View.OnClickListener? {
        return View.OnClickListener {
            if (0 <= position && position < categories.count()) {
                onCategoryListener.invoke(categories[position])
            }
        }
    }

    override fun onViewRecycled(holder: CategoryViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewRecycled(holder)
    }
}

