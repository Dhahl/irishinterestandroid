package com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll

import android.content.Context
import androidx.paging.PagedListAdapter
import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.EndlessBookListAdapter.ItemViewHolder
import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.EndlessBookListAdapter
import com.squareup.picasso.Picasso
import android.view.ViewGroup
import android.view.LayoutInflater
import com.irishinterest.irishinterest.R
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.os.Build
import android.text.Html
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.irishinterest.irishinterest.fragments.mainScreen.BookDetailsActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.annotation.RequiresApi
import com.irishinterest.irishinterest.model.books.Book

class EndlessBookListAdapter(private val context: Context) :
    PagedListAdapter<Book, ItemViewHolder>(
        DIFF_CALLBACK
    ) {
    private val picasso: Picasso
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val book = getItem(position)
        if (book != null) {
            holder.bindView(book)
        }
    }

    inner class ItemViewHolder(v: View?) : RecyclerView.ViewHolder(
        v!!
    ) {
        fun bindView(book: Book) {
            val title: TextView = itemView.findViewById(R.id.bookListItemTitleText)
            val author: TextView = itemView.findViewById(R.id.bookListItemSubtitleText)
            val bookcover: ImageView = itemView.findViewById(R.id.bookListItemImageResource)
            picasso.load("https://irishinterest.ie/upload/" + book.image)
                .placeholder(R.drawable.placeholder)
                .into(bookcover)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                title.setText(
                    Html.fromHtml(
                        if (book.title != null) book.title!!.replace(
                            "'",
                            ""
                        ) else "", Html.FROM_HTML_MODE_COMPACT
                    )
                )
            } else {
                title.setText(
                    Html.fromHtml(
                        if (book.title != null) book.title!!.replace(
                            "'",
                            ""
                        ) else ""
                    )
                )
            }
            //title.setText(book.getTitle() != null ? book.getTitle().replace("'", "") : "");
            author.setText(if (book.author != null && book.author!!.isEmpty()) book.publisher else book.author)
            itemView.setOnClickListener {
                val intent = Intent(context, BookDetailsActivity::class.java)
                if (book.title != null) {
                    intent.putExtra("bookTitle", book.title!!.replace("'", ""))
                } else {
                    intent.putExtra("bookTitle", "")
                }
                intent.putExtra(
                    "bookAuthor",
                    if (book.author!!.isEmpty() || book.author == " ") book.publisher else book.author
                )
                intent.putExtra("bookPublisher", book.publisher)
                intent.putExtra("bookPages", book.pages)
                intent.putExtra("bookPublishDate", book.published)
                intent.putExtra(
                    "bookAvailableAs",
                    if (book.paperback == 0) "Hardcover" else "Paperback"
                )
                intent.putExtra("bookCategory", book.genre)
                intent.putExtra("bookDescription", book.synopsis)
                intent.putExtra("bookImage", book.image)
                intent.putExtra("bookAmazonPage", book.vendorurl)
                intent.putExtra("isbn13", book.isbn13)
                intent.putExtra("bookId", book.id)
                intent.putExtra("authorId", book.authorid)
                intent.putExtra("bookIsbn", book.isbn)
                intent.putExtra("bookUsAsin", book.usAsin)
                intent.putExtra("bookUkAsin", book.usAsin)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Book> =
            object : DiffUtil.ItemCallback<Book>() {
                override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                    return oldItem.id === newItem.id
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }

    init {
        picasso = Picasso.get()
    }
}