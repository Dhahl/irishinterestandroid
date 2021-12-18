package com.irishinterest.irishinterest.network

import android.content.Context
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver
import com.irishinterest.irishinterest.network.api.irishInterest.IrishInterestAPI

/**
 * This is the network adapter class. All network requests go through here.
 * At the moment we only have Irish Interest API connection upon initialization.
 */
class NetworkAdapter(private val guiObserver: GuiObserver, private val context: Context) {
    private var irishInterestAPI: IrishInterestAPI? = null
    private fun initialize() {
        irishInterestAPI = IrishInterestAPI(guiObserver, context)
    }

    init {
        initialize()
    }
}