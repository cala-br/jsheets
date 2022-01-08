package com.jsheets.util;

public class ActionEvent extends BaseEvent<Action> {
  public void fire() {
    getSubscribers().forEach(a -> a.invoke());
  }
}
