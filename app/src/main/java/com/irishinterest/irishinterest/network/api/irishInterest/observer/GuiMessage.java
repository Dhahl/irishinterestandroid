package com.irishinterest.irishinterest.network.api.irishInterest.observer;

import java.util.List;

public class GuiMessage<T extends Data> {
  private Module module;
  private Action action;
  private InternalAction internalAction;
  private T object;
  private List<T> dataList;
  private ScrollAction scrollAction;

  public GuiMessage(Module module, Action action) {
    this.module = module;
    this.action = action;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction){
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, T object) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.object = object;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, ScrollAction scrollAction,T object) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.object = object;
    this.scrollAction = scrollAction;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, List<T> dataList) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.dataList = dataList;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, ScrollAction scrollAction, List<T> dataList) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.dataList = dataList;
    this.scrollAction = scrollAction;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, T object, List<T> dataList) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.object = object;
    this.dataList = dataList;
  }

  public GuiMessage(Module module, Action action, InternalAction internalAction, T object, List<T> dataList, ScrollAction scrollAction) {
    this.module = module;
    this.action = action;
    this.internalAction = internalAction;
    this.object = object;
    this.dataList = dataList;
    this.scrollAction = scrollAction;
  }

  public Module getModule() {
    return module;
  }

  public Action getAction() {
    return action;
  }

  public InternalAction getInternalAction() {
    return internalAction;
  }

  public T getObject() {
    return object;
  }

  public List<T> getDataList() {
    return dataList;
  }

  public ScrollAction getScrollAction() {
    return scrollAction;
  }
}
