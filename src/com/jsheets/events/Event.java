package com.jsheets.events;

import java.util.function.Consumer;

/**
 * A fireable event that carries its {@code sender} and possibly other
 * arguments.
 * 
 * @param <T> A type that extends {@link EventArgs}.
 */
public class Event<T extends EventArgs<?>> extends BaseEvent<Consumer<T>> {
  public void fire(T event) {
    getSubscribers().forEach(c -> c.accept(event));
  }
}
