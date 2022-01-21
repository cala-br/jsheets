package com.jsheets.events;

/**
 * The default arguments of an event.
 * They only carry the {@code sender}.
 */
public class EventArgs<TSender> {
  /**
   * The object that generated the event.
   */
  public final TSender sender;

  public EventArgs(TSender sender) {
    this.sender = sender;
  }
}
