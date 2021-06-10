package com.irishinterest.irishinterest.model.provider;

import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;

import java.util.ArrayList;

abstract public class Provider<T extends Data> {
  protected boolean notification = false;
  protected GuiToModel guiToModel;
  protected NotificationOnChange<T> notificationOnChange;

  public Provider(GuiToModel guiToModel, NotificationOnChange<T> notificationOnChange) {
    this.guiToModel = guiToModel;
    this.notificationOnChange = notificationOnChange;
  }

  public void disableNotifications() {
    notification = false;
  }

  public ArrayList<T> getAllData(){
    notification = true;
    return getData();
  }

  public void pushMessage(GuiMessage guiMessage) {
    guiToModel.publishMessageModel(guiMessage);
  }

  protected abstract ArrayList<T> getData();
  protected abstract void processMessage(GuiMessage<T> guiMessage);
  protected void init(GuiMessage<T> guiMessage) {throw new UnsupportedOperationException("Operation not implemented");}
  protected void add(GuiMessage<T> guiMessage) {throw new UnsupportedOperationException("Operation not implemented");}
  protected void modify(GuiMessage<T> guiMessage) {throw new UnsupportedOperationException("Operation not implemented");}
}
