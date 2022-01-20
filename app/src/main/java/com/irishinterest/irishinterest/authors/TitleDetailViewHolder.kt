package com.irishinterest.irishinterest.authors

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R

class TitleDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title_text: TextView? = itemView.findViewById(R.id.title_text)
    private val detail_text: TextView? = itemView.findViewById(R.id.detail_text)

    fun update(title: String, detail: String) {
        title_text?.text = title
        detail_text?.text = detail
    }
}
