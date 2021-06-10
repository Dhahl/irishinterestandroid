package com.irishinterest.irishinterest.fragments.categories;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.fragments.categories.helper.CategoryListAdapter;
import com.irishinterest.irishinterest.helper.Notification;
import com.irishinterest.irishinterest.model.categories.Category;
import com.irishinterest.irishinterest.model.categories.CategoryValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Action;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.InternalAction;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements NotificationOnChange<CategoryValues> {

    private View view;
    private Context context;
    private CategoryValues categoryValues;
    private CategoryListAdapter categoryListAdapter;
    private RecyclerView categoryRecyclerView;

    private ArrayList<Category> categories;
    private boolean isInitialized = false;

    public CategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(false);
        if (view == null) {
            context = getActivity();
            view = inflater.inflate(R.layout.categories, container, false);
            categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

            categories = new ArrayList<>();
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
        categoryListAdapter = new CategoryListAdapter(categories, getActivity(), this);
        categoryRecyclerView.setAdapter(categoryListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(false);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        categoryRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        if(categoryValues != null) {
            categoryListAdapter.setBooks(categoryValues.getCategoryList());
            categoryListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(Context contextt) {
        super.onAttach(contextt);
        context = contextt;
    }

    @Override
    public void add(CategoryValues data) {
        if (data != null) {
            if(categoryListAdapter != null) {
                this.categoryValues = data;
                categoryListAdapter.setBooks(categoryValues.getCategoryList());
                categoryListAdapter.notifyDataSetChanged();
            }else{
                this.categoryValues = data;
            }
        }
        //if(!isInitialized){
        //    ((Notification) context).onDataReceived(Module.CATEGORIES);
        //    isInitialized = true;
        //}
    }

    @Override
    public void modify(CategoryValues data) {

    }

    @Override
    public void delete(CategoryValues data) {

    }

    @Override
    public void updateAll(CategoryValues data) {

    }

    @Override
    public void registerProvider(Provider<CategoryValues> provider) {
        provider.pushMessage(new GuiMessage(Module.CATEGORIES, Action.INIT, InternalAction.REQUEST));
    }
}
