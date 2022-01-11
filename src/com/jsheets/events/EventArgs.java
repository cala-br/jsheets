package com.jsheets.events;

public class EventArgs<TSender> {
  public final TSender sender;

  public EventArgs(TSender sender) {
    this.sender = sender;
  }
}
