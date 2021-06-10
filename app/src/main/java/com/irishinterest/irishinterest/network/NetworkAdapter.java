package com.irishinterest.irishinterest.network;

import android.content.Context;

import com.irishinterest.irishinterest.network.api.irishInterest.IrishInterestAPI;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver;

/**
 * This is the network adapter class. All network requests go through here.
 * At the moment we only have Irish Interest API connection upon initialization.
 */

public class NetworkAdapter {

    private GuiObserver guiObserver;
    private Context context;
    private IrishInterestAPI irishInterestAPI;

    public NetworkAdapter(GuiObserver guiObserver, Context context) {
        this.guiObserver = guiObserver;
        this.context = context;
        initialize();
    }

    private void initialize(){
        this.irishInterestAPI = new IrishInterestAPI(guiObserver, context);
    }
}
