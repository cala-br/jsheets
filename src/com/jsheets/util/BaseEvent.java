package com.jsheets.util;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEvent<TAction> {
  private final List<TAction> subscribers = new ArrayList<>();
  protected List<TAction> getSubscribers() {
    return subscribers;
  }

  public void subscribe(TAction consumer) {
    subscribers.add(consumer);
  }

  public void unsubscribe(TAction consumer) {
    subscribers.remove(consumer);
  }
}
