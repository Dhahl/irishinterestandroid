package com.irishinterest.irishinterest.authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R

class TitleDetailListingAdapter<Type>(
    private var list: List<Type>,
    private var inflater: LayoutInflater,
    private var titleBinder: (Type) -> String,
    private var detailBinder: (Type) -> String,
    private var clickListener: (Type) -> Unit
) : RecyclerView.Adapter<TitleDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleDetailViewHolder {
        return TitleDetailViewHolder(
            inflater.inflate(
                R.layout.title_detail_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: TitleDetailViewHolder, position: Int) {
        val item: Type = list[position]
        holder.update(titleBinder(item), detailBinder(item))
        holder.itemView.setOnClickListener(onClicked(position))
    }

    private fun onClicked(position: Int): View.OnClickListener? {
        return View.OnClickListener {
            if (0 <= position && position < list.count()) {
                clickListener.invoke(list[position])
            }
        }
    }

    override fun onViewRecycled(holder: TitleDetailViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewRecycled(holder)
    }

}