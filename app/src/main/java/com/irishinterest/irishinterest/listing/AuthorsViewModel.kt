package com.irishinterest.irishinterest.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.irishinterest.irishinterest.webservice.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Comparator

class AuthorsViewModel: ViewModel() {

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
                .map { it.filter { it.alpha.first().isLetter() } }
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
        val uniqueIds = byBookIds.toSortedSet()
        val paramIds: String = uniqueIds.joinToString(separator = ",")
        return LiveDataReactiveStreams.fromPublisher(
            ws.authorsByBookIds(paramIds)
                .onErrorReturnItem(emptyMap<String, List<Author>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


}