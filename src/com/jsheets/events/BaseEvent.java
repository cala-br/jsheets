package com.jsheets.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Base event class from which all
 * events inherit from.
 * 
 * A default method for firing the events is not
 * provided, and must be implemented inside the subclass
 * 
 * @param <TAction>
 *  The action that will be fired for each subscriber
 */
public abstract class BaseEvent<TAction> {
  private final List<TAction> subscribers = new ArrayList<>();
  protected List<TAction> getSubscribers() {
    return subscribers;
  }

  /**
   * Subscribes to this event
   * @param subscriber
   *  An action that will be invoked when the event
   *  is fired
   */
  public synchronized void subscribe(TAction subscriber) {
    subscribers.add(subscriber);
  }

  /**
   * Unsubscribes from this event
   * @param subscriber
   *  An already registered subscriber that has
   *  to be removed
   */
  public synchronized void unsubscribe(TAction subscriber) {
    subscribers.remove(subscriber);
  }
}
