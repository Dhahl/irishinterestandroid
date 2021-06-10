package com.irishinterest.irishinterest.network.api.irishInterest.observer;

import java.util.Observer;

public interface GuiToModel {
  void publishMessageModel(GuiMessage guiMessage);
  void registerObserver(Module module, Observer o);
  void unregisterObserver(Module module, Observer o);
}
