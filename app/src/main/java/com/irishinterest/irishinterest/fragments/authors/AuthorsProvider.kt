package com.irishinterest.irishinterest.fragments.authors

import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange
import com.irishinterest.irishinterest.model.authors.AuthorValues
import com.irishinterest.irishinterest.model.provider.Provider
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module
import java.util.*
import kotlin.collections.ArrayList

class AuthorsProvider(
    guiToModel: GuiToModel,
    notificationOnChange: NotificationOnChange<AuthorValues>,
    override val data: ArrayList<AuthorValues> = ArrayList<AuthorValues>()
) : Provider<AuthorValues>(
    guiToModel, notificationOnChange
), Observer {
    protected override fun processMessage(guiMessage: GuiMessage<AuthorValues>?) {}
    override fun update(o: Observable, arg: Any) {}

    init {
        this.notificationOnChange.registerProvider(this)
        this.guiToModel.registerObserver(Module.AUTHORS, this)
    }
}