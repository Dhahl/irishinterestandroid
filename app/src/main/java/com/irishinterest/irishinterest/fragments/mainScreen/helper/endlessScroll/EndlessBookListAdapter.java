package com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.fragments.mainScreen.BookDetailsActivity;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.model.books.Book;
import com.squareup.picasso.Picasso;

public class EndlessBookListAdapter extends PagedListAdapter<Book, EndlessBookListAdapter.ItemViewHolder> {

    private Context context;
    private Picasso picasso;

    public EndlessBookListAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
        this.picasso = Picasso.with(context);
    }

    @NonNull
    @Override
    public EndlessBookListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndlessBookListAdapter.ItemViewHolder holder, int position) {
        Book book = getItem(position);
        if(book != null) {
            holder.bindView(book);
        }
    }

    private static DiffUtil.ItemCallback<Book> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Book>() {
                @Override
                public boolean areItemsTheSame(Book oldItem, Book newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(Book oldItem, Book newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View v) {
            super(v);
        }

        private ImageView bookcover;
        private TextView title;
        private TextView author;

        public void bindView(Book book){
            title = itemView.findViewById(R.id.bookListItemTitleText);
            author = itemView.findViewById(R.id.bookListItemSubtitleText);
            bookcover = itemView.findViewById(R.id.bookListItemImageResource);

            picasso.load("https://irishinterest.ie/upload/" + book.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(bookcover);
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               title.setText(Html.fromHtml(book.getTitle() != null ? book.getTitle().replace("'", "") : "", Html.FROM_HTML_MODE_COMPACT));
           } else {
               title.setText(Html.fromHtml(book.getTitle() != null ? book.getTitle().replace("'", "") : ""));
           }
            //title.setText(book.getTitle() != null ? book.getTitle().replace("'", "") : "");
            author.setText(book.getAuthor() != null && book.getAuthor().isEmpty() ? book.getPublisher() : book.getAuthor());

            final Book tmpBook = book;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetailsActivity.class);
                    if(tmpBook.getTitle() != null) {
                        intent.putExtra("bookTitle", tmpBook.getTitle().replace("'", ""));
                    } else {
                        intent.putExtra("bookTitle", "");
                    }
                    intent.putExtra("bookAuthor", tmpBook.getAuthor().isEmpty() || tmpBook.getAuthor().equals(" ") ? tmpBook.getPublisher() : tmpBook.getAuthor());
                    intent.putExtra("bookPublisher", tmpBook.getPublisher());
                    intent.putExtra("bookPages", tmpBook.getPages());
                    intent.putExtra("bookPublishDate", tmpBook.getPublished());
                    intent.putExtra("bookAvailableAs", tmpBook.getPaperback() == 0 ? "Hardcover" : "Paperback");
                    intent.putExtra("bookCategory", tmpBook.getGenre());
                    intent.putExtra("bookDescription", tmpBook.getSynopsis());
                    intent.putExtra("bookImage", tmpBook.getImage());
                    intent.putExtra("bookAmazonPage", tmpBook.getVendorurl());
                    intent.putExtra("isbn13", tmpBook.getIsbn13());
                    intent.putExtra("bookId", tmpBook.getId());
                    intent.putExtra("authorId", tmpBook.getAuthorid());
                    intent.putExtra("bookIsbn", tmpBook.getIsbn());
                    intent.putExtra("bookUsAsin", tmpBook.getUsAsin());
                    intent.putExtra("bookUkAsin", tmpBook.getUsAsin());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
