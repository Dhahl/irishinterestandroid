package com.irishinterest.irishinterest.network.api.irishInterest;

import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;

public interface NotificationOnChange<T extends Data> {
  void add(T data);
  void modify(T data);
  void delete(T data);
  void updateAll(T data);
  void registerProvider(Provider<T> provider);
}
