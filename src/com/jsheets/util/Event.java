package com.jsheets.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Event<T> {
  private final List<Consumer<T>> consumers = new ArrayList<>();

  public void subscribe(Consumer<T> consumer) {
    consumers.add(consumer);
  }

  public void unsubscribe(Consumer<T> consumer) {
    consumers.remove(consumer);
  }

  public void fire(T event) {
    consumers.forEach(c -> c.accept(event));
  }
}
