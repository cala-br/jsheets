package com.jsheets.events;

import java.util.function.Consumer;

public class Event<T extends EventArgs<?>> extends BaseEvent<Consumer<T>> {
  public void fire(T event) {
    getSubscribers().forEach(c -> c.accept(event));
  }
}
