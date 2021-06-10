package com.irishinterest.irishinterest.fragments.authors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.AuthorsActivity;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.fragments.authors.helper.AuthorViewModel;
import com.irishinterest.irishinterest.fragments.authors.helper.AuthorsListAdapter;
import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.model.authors.AuthorValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;


public class AuthorsFragment extends Fragment implements NotificationOnChange<AuthorValues> {

    private View view;
    private Context context;
    private RecyclerView authorsRecylcerView;
    private EditText authorSearch;
    private Button filterButton;


    public AuthorsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(false);
        if (view == null) {
            context = this.getContext();
            view = inflater.inflate(R.layout.authors_screen_fragment, container, false);
            authorsRecylcerView = view.findViewById(R.id.authorListRecyclerView);
            authorSearch = view.findViewById(R.id.searchAuthorsEditText);
            filterButton = view.findViewById(R.id.filterButton);

            authorSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(context, AuthorsActivity.class);
                    intent.putExtra("type", "authors");
                    intent.putExtra("query", authorSearch.getText().toString());
                    context.startActivity(intent);
                    return true;
                }
                return false;
            });


            filterButton.setOnClickListener(v ->{
                Toast.makeText(context, "Not yet implemented.", Toast.LENGTH_SHORT).show();
            });

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //So that we can check data ready
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                config();
            }
        });
    }

    private void config() {
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new AuthorViewModel(Module.AUTHORS, null);
            }
        };

        AuthorViewModel bookViewModel = ViewModelProviders.of(this, factory).get(AuthorViewModel.class);
        final AuthorsListAdapter authorsListAdapter = new AuthorsListAdapter(this.getContext());

        bookViewModel.authorPagedList.observe(this, new Observer<PagedList<Author>>() {
            @Override
            public void onChanged(PagedList<Author> authors) {
                authorsListAdapter.submitList(authors);
            }
        });

        authorsRecylcerView.setAdapter(authorsListAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(false);
        authorsRecylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        authorsRecylcerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void add(AuthorValues data) {

    }

    @Override
    public void modify(AuthorValues data) {

    }

    @Override
    public void delete(AuthorValues data) {

    }

    @Override
    public void updateAll(AuthorValues data) {

    }

    @Override
    public void registerProvider(Provider<AuthorValues> provider) {

    }
}
