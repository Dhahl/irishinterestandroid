package com.irishinterest.irishinterest.network.api.irishInterest.observer;

import java.util.Observer;

public interface ModelToGui {
  void publishMessage(GuiMessage guiMessage);
  void registerObserverModel(Module module, Observer o);
  void unregisterObserverModel(Module module, Observer o);
}
