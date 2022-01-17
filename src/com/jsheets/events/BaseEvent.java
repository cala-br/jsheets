package com.jsheets.events;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEvent<TAction> {
  private final List<TAction> subscribers = new ArrayList<>();
  protected List<TAction> getSubscribers() {
    return subscribers;
  }

  public synchronized void subscribe(TAction consumer) {
    subscribers.add(consumer);
  }

  public synchronized void unsubscribe(TAction consumer) {
    subscribers.remove(consumer);
  }
}
