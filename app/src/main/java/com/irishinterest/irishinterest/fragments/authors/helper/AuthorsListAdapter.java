package com.irishinterest.irishinterest.fragments.authors.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.BooksActivity;
import com.irishinterest.irishinterest.model.authors.Author;


public class AuthorsListAdapter extends PagedListAdapter<Author, AuthorsListAdapter.ViewHolder> {
    private Context context;

    public AuthorsListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }

        private TextView authorName;

        public void bindView(Author author){
            authorName = itemView.findViewById(R.id.authorName);
            authorName.setText(author.getFirstname() + " " + author.getLastname());
            authorName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BooksActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("type", "authors");
                    if(author.getFirstname() != null) {
                        intent.putExtra("firstName", author.getFirstname().replace("'", ""));
                    } else {
                        intent.putExtra("firstName", "");
                    }
                    if(author.getFirstname() != null){
                        intent.putExtra("lastName", author.getLastname().replace("'", ""));
                    } else {
                        intent.putExtra("lastName", "");
                    }

                    intent.putExtra("id", String.valueOf(author.getId()));
                    intent.putExtra("profile", author.getProfile() == null || author.getProfile().isEmpty()? "Author information pending." : author.getProfile());
                    intent.putExtra("authorImage", author.getImage() == null ? null : author.getImage().toString());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public AuthorsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.author_list_item, parent, false);
        return new AuthorsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsListAdapter.ViewHolder holder, int position) {
        Author author = getItem(position);
        if(author != null) {
            holder.bindView(author);
        }
    }


    private static DiffUtil.ItemCallback<Author> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Author>() {
                @Override
                public boolean areItemsTheSame(Author oldItem, Author newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(Author oldItem, Author newItem) {
                    return oldItem.equals(newItem);
                }
            };

}
