package com.irishinterest.irishinterest.network.api.irishInterest.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GuiObserver implements ModelToGui, GuiToModel {

    private Map<Module, GuiMessageObserver> moduleGuiMessageObserverMap;

    public GuiObserver() {
        moduleGuiMessageObserverMap = new HashMap<>();
        Module[] modules = Module.values();
        for (Module module : modules) {
            moduleGuiMessageObserverMap.put(module, new GuiMessageObserver());
        }
    }

    @Override
    public void registerObserver(Module module, Observer o) {
        moduleGuiMessageObserverMap.get(module).addObserver(o);
    }

    @Override
    public void unregisterObserver(Module module, Observer o) {
        moduleGuiMessageObserverMap.get(module).deleteObserver(o);
    }


    @Override
    public void registerObserverModel(Module module, Observer o) {
        moduleGuiMessageObserverMap.get(module).addObserver(o);
    }

    @Override
    public void unregisterObserverModel(Module module, Observer o) {
        moduleGuiMessageObserverMap.get(module).deleteObserver(o);
    }

    @Override
    public void publishMessage(GuiMessage guiMessage) {
        moduleGuiMessageObserverMap.get(guiMessage.getModule()).publishMessage(guiMessage);
    }

    @Override
    public void publishMessageModel(GuiMessage guiMessage) {
        moduleGuiMessageObserverMap.get(guiMessage.getModule()).publishMessage(guiMessage);
    }

    private class GuiMessageObserver extends Observable {
        void publishMessage(GuiMessage guiMessage) {
            setChanged();
            notifyObservers(guiMessage);
        }
    }
}
