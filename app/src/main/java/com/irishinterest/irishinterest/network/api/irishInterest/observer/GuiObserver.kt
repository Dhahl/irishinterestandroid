package com.irishinterest.irishinterest.network.api.irishInterest.observer

import com.irishinterest.irishinterest.network.api.irishInterest.observer.ModelToGui
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver.GuiMessageObserver
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage
import java.util.*

class GuiObserver : ModelToGui, GuiToModel {
    private val moduleGuiMessageObserverMap: MutableMap<Module, GuiMessageObserver>
    override fun registerObserver(module: Module, o: Observer) {
        moduleGuiMessageObserverMap[module]!!.addObserver(o)
    }

    override fun unregisterObserver(module: Module, o: Observer) {
        moduleGuiMessageObserverMap[module]!!.deleteObserver(o)
    }

    override fun registerObserverModel(module: Module, o: Observer) {
        moduleGuiMessageObserverMap[module]!!.addObserver(o)
    }

    override fun unregisterObserverModel(module: Module, o: Observer) {
        moduleGuiMessageObserverMap[module]!!.deleteObserver(o)
    }

    override fun publishMessage(guiMessage: GuiMessage<*>) {
        moduleGuiMessageObserverMap[guiMessage.module]!!.publishMessage(guiMessage)
    }

    override fun publishMessageModel(guiMessage: GuiMessage<*>) {
        moduleGuiMessageObserverMap[guiMessage.module]!!.publishMessage(guiMessage)
    }

    private inner class GuiMessageObserver : Observable() {
        fun publishMessage(guiMessage: GuiMessage<*>?) {
            setChanged()
            notifyObservers(guiMessage)
        }
    }

    init {
        moduleGuiMessageObserverMap = HashMap()
        val modules = Module.values()
        for (module in modules) {
            moduleGuiMessageObserverMap[module] = GuiMessageObserver()
        }
    }
}