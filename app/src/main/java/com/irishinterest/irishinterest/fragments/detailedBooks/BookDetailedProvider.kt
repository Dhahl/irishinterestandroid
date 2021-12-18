package com.irishinterest.irishinterest.fragments.detailedBooks

import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange
import com.irishinterest.irishinterest.model.books.BookValues
import com.irishinterest.irishinterest.model.provider.Provider
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module
import java.util.*

class BookDetailedProvider(
    guiToModel: GuiToModel,
    notificationOnChange: NotificationOnChange<BookValues>,
    override val data: ArrayList<BookValues> = ArrayList<BookValues>()
) : Provider<BookValues>(guiToModel, notificationOnChange), Observer {

    protected override fun processMessage(guiMessage: GuiMessage<BookValues>?) {}
    override fun update(o: Observable, arg: Any) {}

    init {
        this.notificationOnChange.registerProvider(this)
        this.guiToModel.registerObserver(Module.BOOK_DETAILED, this)
    }
}