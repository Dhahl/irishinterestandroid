package com.irishinterest.irishinterest.fragments.mainScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.BookViewModel;
import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.EndlessBookListAdapter;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.books.BookValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class MainScreenFragment extends Fragment implements NotificationOnChange<BookValues> {
    public static final String FRAGMENT_NAME = "MAIN_SCREEN_FRAGMENT";

    private View view = null;

    private RecyclerView recyclerView;
    private LottieAnimationView loadingAnimation;
    private Context context;

    public MainScreenFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(false);
        if (view == null) {
            context = getActivity();
            view = inflater.inflate(R.layout.main_screen, container, false);
            recyclerView = view.findViewById(R.id.bookListRecyclerView);
            loadingAnimation = view.findViewById(R.id.loadingCircle);
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
                return (T) new BookViewModel(Module.MAIN_SCREEN, null);
            }
        };

        BookViewModel bookViewModel = ViewModelProviders.of(this, factory).get(BookViewModel.class);
        final EndlessBookListAdapter endlessBookListAdapter = new EndlessBookListAdapter(this.getContext());

        bookViewModel.bookPagedList.observe(this, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                endlessBookListAdapter.submitList(books);
                if(recyclerView.getVisibility() == View.GONE){
                    recyclerView.setVisibility(View.VISIBLE);
                    loadingAnimation.setVisibility(View.GONE);
                }
            }
        });

        recyclerView.setAdapter(endlessBookListAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //Notification on change implementation
    @Override
    public void add(BookValues data) {
    }

    @Override
    public void modify(BookValues data) {

    }

    @Override
    public void delete(BookValues data) {

    }

    @Override
    public void updateAll(BookValues data) {

    }

    @Override
    public void registerProvider(Provider<BookValues> provider) {

    }
}
