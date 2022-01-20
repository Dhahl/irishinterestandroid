package com.irishinterest.irishinterest.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.irishinterest.irishinterest.webservice.*
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthorsViewModel : ViewModel() {

    private val ws: WebService = Repository().service

    fun authors(): LiveData<List<Author>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authors()
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsSearchByName(name: String): LiveData<List<Author>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorsSearchByName(name)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsByLetter(letter: String): LiveData<List<Author>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorsByLetter(letter)
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsCount(): LiveData<Int> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorsCount()
                .onErrorReturnItem(42499)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsAtoZCount(): LiveData<List<CountByLetter>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorsAtoZCount()
//                .map { it.filter { letter -> letter.alpha.first().isLetter() == false } }
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorDetails(authorId: Int): LiveData<AuthorDetails> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorDetails(authorId)
                .onErrorReturnItem(AuthorDetails.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsByBookIds(byBookIds: List<Int>): LiveData<AuthorsOfBooks> {
        return LiveDataReactiveStreams.fromPublisher(
            authorsFlowableByBookIds(byBookIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun authorsOfBooksFlowable(ofBookFlowable: Flowable<List<Book>>): LiveData<AuthorsOfBooks> {
        return LiveDataReactiveStreams.fromPublisher(
            ofBookFlowable.map {
                it.map { it.id }
            }.flatMap {
                authorsFlowableByBookIds(it)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    private fun authorsFlowableByBookIds(bookIds: List<Int>): Flowable<AuthorsOfBooks> {
        val uniqueIds = bookIds.toSortedSet()
        val paramIds: String = uniqueIds.joinToString(separator = ",")
        return ws.authorsByBookIds(paramIds)
            .onErrorReturnItem(emptyMap<String, List<Author>>())
    }

}