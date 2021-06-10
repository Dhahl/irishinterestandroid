package com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public class BookViewModel extends ViewModel {

    public LiveData<PagedList<Book>> bookPagedList;
    public LiveData<PageKeyedDataSource<Integer, Book>> liveDataSource;
    public BookDataSourceFactory bookDataSourceFactory;

    private Module module;
    private Object object;

    public BookViewModel(Module module, Object object) {
        this.module = module;
        this.object = object;

        bookDataSourceFactory = new BookDataSourceFactory(module, object);
        liveDataSource = bookDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(BookDataSource.PAGE_SIZE).build();

        bookPagedList = (new LivePagedListBuilder(bookDataSourceFactory, pagedListConfig)).build();
    }
}
