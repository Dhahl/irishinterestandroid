package com.irishinterest.irishinterest.model.provider

import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage
import java.lang.UnsupportedOperationException
import java.util.ArrayList

abstract class Provider<T : Data?>(
    @JvmField protected var guiToModel: GuiToModel,
    @JvmField protected var notificationOnChange: NotificationOnChange<T>
) {
    @JvmField
    protected var notification = false
    fun disableNotifications() {
        notification = false
    }

    val allData: ArrayList<T>
        get() {
            notification = true
            return data
        }

    fun pushMessage(guiMessage: GuiMessage<*>?) {
        guiToModel.publishMessageModel(guiMessage!!)
    }

    protected abstract val data: ArrayList<T>
    protected abstract fun processMessage(guiMessage: GuiMessage<T>?)
    protected open fun init(guiMessage: GuiMessage<T>?) {
        throw UnsupportedOperationException("Operation not implemented")
    }

    protected open fun add(guiMessage: GuiMessage<T>?) {
        throw UnsupportedOperationException("Operation not implemented")
    }

    protected open fun modify(guiMessage: GuiMessage<T>?) {
        throw UnsupportedOperationException("Operation not implemented")
    }
}