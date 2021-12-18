package com.irishinterest.irishinterest.helper

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module

interface Notification {
    fun onDataReceived(module: Module)
}