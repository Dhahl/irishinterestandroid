package com.irishinterest.irishinterest.network.api.irishInterest.observer

import java.util.*

interface ModelToGui {
    fun publishMessage(guiMessage: GuiMessage<*>)
    fun registerObserverModel(module: Module, o: Observer)
    fun unregisterObserverModel(module: Module, o: Observer)
}