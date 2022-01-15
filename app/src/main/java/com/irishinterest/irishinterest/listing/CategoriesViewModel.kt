package com.irishinterest.irishinterest.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.irishinterest.irishinterest.webservice.Category
import com.irishinterest.irishinterest.webservice.Repository
import com.irishinterest.irishinterest.webservice.WebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel: ViewModel() {
    private val ws: WebService = Repository().service

    fun categories(): LiveData<List<Category>> {
        return LiveDataReactiveStreams.fromPublisher(
            ws.categories()
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }
}
