package com.irishinterest.irishinterest.fragments.authors.helper;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class AuthorDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Author>> itemLiveDataSource = new MutableLiveData<>();

    private Module module;
    private Object object;

    public AuthorDataSourceFactory(Module module, Object object) {
        this.module = module;
        this.object = object;
    }

    @Override
    public DataSource<Integer, Author> create() {
        AuthorDataSource authorDataSource = new AuthorDataSource(module, object);

        itemLiveDataSource.postValue(authorDataSource);

        return authorDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Author>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
