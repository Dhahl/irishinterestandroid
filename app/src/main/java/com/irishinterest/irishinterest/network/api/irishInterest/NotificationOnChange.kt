package com.irishinterest.irishinterest.network.api.irishInterest

import com.irishinterest.irishinterest.model.provider.Provider
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data

interface NotificationOnChange<T : Data?> {
    fun add(data: T)
    fun modify(data: T)
    fun delete(data: T)
    fun updateAll(data: T)
    fun registerProvider(provider: Provider<T>?)
}