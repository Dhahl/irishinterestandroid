package com.irishinterest.irishinterest.categories

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleView: TextView? = itemView.findViewById(R.id.categoryText)

    fun update(name: String) {
        titleView?.text = name
    }

}
