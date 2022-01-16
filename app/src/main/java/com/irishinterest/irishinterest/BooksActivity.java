package com.irishinterest.irishinterest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.user.UserValues;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;
import com.squareup.picasso.Picasso;


public class BooksActivity extends AppCompatActivity {

    private RecyclerView categoryBooksRecyclerView;
    private LinearLayout categoryBookContainer;
    private TextView categoryText;
    private TextView authorDescription;
    private ImageView authorImage;
    private LinearLayout authorDescriptionContainer;
    private LinearLayout divider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_books);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_out_right);
            }
        });

        categoryBooksRecyclerView = findViewById(R.id.categoryBookRecyclerView);
        categoryText = findViewById(R.id.categoryText);
        authorDescriptionContainer = findViewById(R.id.authorDescriptionContainer);
        authorImage = findViewById(R.id.authorImage);
        authorDescription = findViewById(R.id.authorDescriptionText);
        divider = findViewById(R.id.dividerLineAuthor);



        final Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String type = extras.getString("type");
            if(type.equals("category")){
                categoryText.setText(extras.getString("categoryName"));
                configureBooksRecyclerView(String.valueOf(extras.getInt("categoryId")), Module.CATEGORIES);
            } else if(type.equals("authors")){
                categoryText.setText(extras.getString("firstName") + ", " + extras.getString("lastName"));
                configureBooksRecyclerView(extras.getString("id"), Module.AUTHORS);
                authorDescriptionContainer.setVisibility(View.VISIBLE);
                authorDescription.setText(extras.getString("profile"));
                Picasso.get().load("https://irishinterest.ie/upload/" + extras.getString("authorImage"))
                        .placeholder(R.drawable.noauthor)
                        .into(authorImage);
                divider.setVisibility(View.VISIBLE);

            } else if(type.equals("bookSearch")){
                categoryText.setText(extras.getString("query"));
                configureBooksRecyclerView(extras.getString("query"), Module.BOOK_SEARCH);
            } else if(type.equals("comingSoon")){
                categoryText.setText("Coming soon");
                configureBooksRecyclerView(extras.getString(""), Module.BOOK_COMING_SOON);
            } else if(type.equals("topSearched")){
                categoryText.setText("Top searched");
                configureBooksRecyclerView(extras.getString(""), Module.BOOK_TOP_SEARCHED);
            } else if(type.equals("favourites")){
                categoryText.setText("Favourites");
                configureBooksRecyclerView(extras.getString(""), Module.FAVOURITES);
            }
        }
    }

    public void configureBooksRecyclerView(String idd, Module module){
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(module != Module.BOOK_SEARCH && module != Module.BOOK_TOP_SEARCHED && module != Module.BOOK_COMING_SOON && module != Module.FAVOURITES){
                    Integer id = Integer.parseInt(idd);
                    return (T) new BookViewModel(module, id);
                } else if(module == Module.FAVOURITES){
                    UserValues user = UserController.getUser(getApplicationContext());
                    return (T) new BookViewModel(module, user.getId());
                }
                return (T) new BookViewModel(module, idd);
            }
        };

        BookViewModel bookViewModel = ViewModelProviders.of(this, factory).get(BookViewModel.class);
        final EndlessBookListAdapter endlessBookListAdapter = new EndlessBookListAdapter(getBaseContext());


        bookViewModel.bookPagedList.observe(this, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                endlessBookListAdapter.submitList(books);
            }
        });

        categoryBooksRecyclerView.setAdapter(endlessBookListAdapter);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(false);
        categoryBooksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        categoryBooksRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

}
