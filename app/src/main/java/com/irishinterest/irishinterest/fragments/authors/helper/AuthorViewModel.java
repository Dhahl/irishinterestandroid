package com.irishinterest.irishinterest.fragments.authors.helper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll.BookDataSource;
import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class AuthorViewModel extends ViewModel {

    public LiveData<PagedList<Author>> authorPagedList;
    public LiveData<PageKeyedDataSource<Integer, Author>> liveDataSource;

    private Module module;
    private Object object;

    public AuthorViewModel(Module module, Object object) {
        this.module = module;
        this.object = object;

        AuthorDataSourceFactory authorDataSourceFactory = new AuthorDataSourceFactory(module, object);
        liveDataSource = authorDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(BookDataSource.PAGE_SIZE).build();

        authorPagedList = (new LivePagedListBuilder(authorDataSourceFactory, pagedListConfig)).build();
    }
}
