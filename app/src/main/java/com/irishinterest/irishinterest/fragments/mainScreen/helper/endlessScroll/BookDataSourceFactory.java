package com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class BookDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Book>> itemLiveDataSource = new MutableLiveData<>();

    private Module module;
    private Object object;

    public BookDataSourceFactory(Module module, Object object) {
        this.module = module;
        this.object = object;
    }

    @Override
    public DataSource<Integer, Book> create() {
        BookDataSource bookDataSource = new BookDataSource(module, object);

        itemLiveDataSource.postValue(bookDataSource);

        return bookDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Book>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
