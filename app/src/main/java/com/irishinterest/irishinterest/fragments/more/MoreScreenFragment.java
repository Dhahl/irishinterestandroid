package com.irishinterest.irishinterest.fragments.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.irishinterest.irishinterest.BooksActivity;
import com.irishinterest.irishinterest.PublishedBooksActivity;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.user.UserValues;

public class MoreScreenFragment extends Fragment {

    private View view;
    private LinearLayout publishedBooks;
    private LinearLayout topSearches;
    private LinearLayout comingSoon;
    private LinearLayout favourites;
    private Context context;

    public MoreScreenFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(false);
        if (view == null) {
            context = this.getContext();
            view = inflater.inflate(R.layout.more_screen_fragment, container, false);
            publishedBooks = view.findViewById(R.id.publishedBooks);
            topSearches = view.findViewById(R.id.topSearches);
            comingSoon = view.findViewById(R.id.comingSoon);
            favourites = view.findViewById(R.id.favourites);

            publishedBooks.setOnClickListener(v->{
                Intent intent = new Intent(context, PublishedBooksActivity.class);
                context.startActivity(intent);
            });

            topSearches.setOnClickListener(v->{
                Intent intent = new Intent(getActivity(), BooksActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "topSearched");
                getActivity().startActivity(intent);
            });

            comingSoon.setOnClickListener(v->{
                Intent intent = new Intent(getActivity(), BooksActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "comingSoon");
                getActivity().startActivity(intent);
            });

            favourites.setOnClickListener(v->{
                UserValues user = UserController.getUser(context);
                if(user != null) {
                    Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("type", "favourites");
                    getActivity().startActivity(intent);
                } else {
                    Toast.makeText(context, "To access favourites you have to be logged in. Please login or create an account.", Toast.LENGTH_LONG).show();
                }
            });
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
