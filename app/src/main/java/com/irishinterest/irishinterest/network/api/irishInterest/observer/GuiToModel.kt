package com.irishinterest.irishinterest.network.api.irishInterest.observer

import java.util.*

interface GuiToModel {
    fun publishMessageModel(guiMessage: GuiMessage<*>)
    fun registerObserver(module: Module, o: Observer)
    fun unregisterObserver(module: Module, o: Observer)
}