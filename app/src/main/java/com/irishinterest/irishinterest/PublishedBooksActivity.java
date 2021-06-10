package com.irishinterest.irishinterest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.BookViewModel;
import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.EndlessBookListAdapter;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class PublishedBooksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText searchBooks;
    private PublishedBooksActivity publishedBooksActivity;
    private BookViewModel bookViewModel;
    private EndlessBookListAdapter endlessBookListAdapter;
    private Observer<PagedList<Book>> observer;
    private ViewModelProvider.Factory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        publishedBooksActivity = this;

        setContentView(R.layout.published_books_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());


        recyclerView = findViewById(R.id.publishedBooksRecyclerView);
        searchBooks = findViewById(R.id.searchBooksEditText);

        searchBooks.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //configureBooksRecyclerView(recyclerView,searchBooks.getText().toString(), Module.BOOK_SEARCH, true);
                //Toast.makeText(publishedBooksActivity, "Not yet implemented.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), BooksActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "bookSearch");
                intent.putExtra("query", searchBooks.getText().toString());
                getBaseContext().startActivity(intent);

                return true;
            }
            return false;
        });

        configureBooksRecyclerView(recyclerView,"0", Module.MAIN_SCREEN, false);
    }

    public void configureBooksRecyclerView(RecyclerView recy, String idd, Module module, boolean clear){
         factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new BookViewModel(module, idd);
            }
        };

        observer = new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                endlessBookListAdapter.submitList(books);
            }
        };

        bookViewModel = ViewModelProviders.of(this, factory).get(BookViewModel.class);


        endlessBookListAdapter = new EndlessBookListAdapter(getBaseContext());
        bookViewModel.bookPagedList.observe(this, observer);

        recy.setAdapter(endlessBookListAdapter);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(false);
        recy.setLayoutManager(new GridLayoutManager(this, 2));
        recy.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


}
