package com.irishinterest.irishinterest.helper;

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

public interface Notification {
    void onDataReceived(Module module);
}
