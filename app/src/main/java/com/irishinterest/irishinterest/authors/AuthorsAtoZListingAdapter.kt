package com.irishinterest.irishinterest.authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R
import com.irishinterest.irishinterest.categories.CategoryViewHolder
import com.irishinterest.irishinterest.webservice.Category
import com.irishinterest.irishinterest.webservice.CountByLetter

class AuthorsAtoZListingAdapter(private var list : List<CountByLetter>,
                                private var inflater: LayoutInflater,
                                private val onSelected: (CountByLetter) -> Unit) : RecyclerView.Adapter<TitleDetailViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleDetailViewHolder {
            return TitleDetailViewHolder(inflater.inflate(R.layout.title_detail_list_item, parent, false))
        }

        override fun getItemCount(): Int {
            return list.count()
        }

        override fun onBindViewHolder(holder: TitleDetailViewHolder, position: Int) {
            val item: CountByLetter = list[position]
            holder.update(item.alpha, item.count.toString())
            holder.itemView.setOnClickListener(onClicked(position))
        }

        private fun onClicked(position: Int): View.OnClickListener? {
            return View.OnClickListener {
                if (0 <= position && position < list.count()) {
                    onSelected.invoke(list[position])
                }
            }
        }

        override fun onViewRecycled(holder: TitleDetailViewHolder) {
            holder.itemView.setOnClickListener(null)
            super.onViewRecycled(holder)
        }
}
