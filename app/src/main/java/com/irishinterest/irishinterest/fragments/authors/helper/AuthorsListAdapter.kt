package com.irishinterest.irishinterest.fragments.authors.helper

import android.content.Context
import androidx.paging.PagedListAdapter
import com.irishinterest.irishinterest.model.authors.Author
import com.irishinterest.irishinterest.fragments.authors.helper.AuthorsListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.irishinterest.irishinterest.R
import android.content.Intent
import com.irishinterest.irishinterest.BooksActivity
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.annotation.RequiresApi
import android.os.Build
import android.view.View

class AuthorsListAdapter(private val context: Context) :
    PagedListAdapter<Author, AuthorsListAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {
    inner class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
        fun bindView(author: Author) {
            val authorName: TextView = itemView.findViewById(R.id.authorName)
            authorName.setText(author.firstname + " " + author.lastname)
            authorName.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, BooksActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("type", "authors")
                if (author.firstname != null) {
                    intent.putExtra("firstName", author.firstname!!.replace("'", ""))
                } else {
                    intent.putExtra("firstName", "")
                }
                if (author.firstname != null) {
                    intent.putExtra("lastName", author.lastname!!.replace("'", ""))
                } else {
                    intent.putExtra("lastName", "")
                }
                intent.putExtra("id", author.id.toString())
                intent.putExtra(
                    "profile",
                    if (author.profile == null || author.profile!!.isEmpty()) "Author information pending." else author.profile
                )
                intent.putExtra(
                    "authorImage",
                    if (author.image == null) null else author.image.toString()
                )
                context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.author_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val author = getItem(position)
        if (author != null) {
            holder.bindView(author)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Author> =
            object : DiffUtil.ItemCallback<Author>() {
                override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
                    return oldItem.id === newItem.id
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}