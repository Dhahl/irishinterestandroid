package com.irishinterest.irishinterest.fragments.detailedBooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.model.books.BookValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;

public class BookDetailedFragment extends Fragment implements NotificationOnChange<BookValues> {
    private View view;

    public BookDetailedFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(false);
        if (view == null) {
            view = inflater.inflate(R.layout.main_screen, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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
