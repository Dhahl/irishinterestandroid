package com.irishinterest.irishinterest;

import android.os.Bundle;
import android.view.View;
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

import com.irishinterest.irishinterest.fragments.authors.helper.AuthorViewModel;
import com.irishinterest.irishinterest.fragments.authors.helper.AuthorsListAdapter;
import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class AuthorsActivity extends AppCompatActivity {
    private RecyclerView authorsRecyclerView;
    private TextView authorText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_searched);

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

        authorsRecyclerView = findViewById(R.id.authorListRecyclerView);
        authorText = findViewById(R.id.categoryText);

        final Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String type = extras.getString("type");
            if(type.equals("authors")){
                authorText.setText(extras.getString("query"));
                configureBooksRecyclerView(extras.getString("query"), Module.AUTHOR_SEARCH);
            }
        }
    }

    public void configureBooksRecyclerView(String idd, Module module){
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new AuthorViewModel(module, idd);
            }
        };

        AuthorViewModel authorViewModel = ViewModelProviders.of(this, factory).get(AuthorViewModel.class);
        final AuthorsListAdapter authorsListAdapter = new AuthorsListAdapter(getBaseContext());

        authorViewModel.authorPagedList.observe(this, new Observer<PagedList<Author>>() {
            @Override
            public void onChanged(PagedList<Author> authors) {
                authorsListAdapter.submitList(authors);
            }
        });

        authorsRecyclerView.setAdapter(authorsListAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(false);
        authorsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        authorsRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
