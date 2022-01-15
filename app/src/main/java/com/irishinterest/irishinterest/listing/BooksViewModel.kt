package com.irishinterest.irishinterest.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.irishinterest.irishinterest.webservice.Book
import com.irishinterest.irishinterest.webservice.BookDetails
import com.irishinterest.irishinterest.webservice.Repository
import com.irishinterest.irishinterest.webservice.WebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BooksViewModel: ViewModel() {

    private val ws: WebService = Repository().service
    private val pageSize: Int = 30

    fun booksSearchByName(name: String): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksSearchByName(name)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun booksByAuthorID(authorID: Int, page: Int): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksByAuthorID(authorID, page * pageSize)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun booksByCategoryId(categoryId: Int, page: Int): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksByCategoryId(categoryId, page * pageSize)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun booksLatest(page: Int): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksLatest(page * pageSize)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun booksPublished(page: Int): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksPublished(page * pageSize)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun booksComingSoon(page: Int): LiveData<List<Book>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.booksComingSoon(page * pageSize)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun bookDetails(bookID: Int): LiveData<BookDetails> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.bookDetails(bookID)
                .onErrorReturnItem(BookDetails.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

}
