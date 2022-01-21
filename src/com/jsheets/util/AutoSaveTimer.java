package com.jsheets.util;

import java.awt.event.ActionEvent;

import javax.swing.Timer;
import com.jsheets.services.ServiceRepository;

/**
 * Saves the current session at the given interval, using
 * the {@link SessionService}'s {@code saveCurrentSession} method.
 */
public class AutoSaveTimer extends Timer {
  public static final int oneSecond = 1000;
  public static final int oneMinute = 60 * oneSecond;
  public static final int twoMinutes = 2 * oneMinute;
  public static final int threeMinutes = 3 * oneMinute;


  /**
   * Creates a new {@code AutoSaveTimer} with the given
   * interval.
   * @param delayMs
   *  The interval at which each file will be saved, in milliseconds.
   *
   * @return
   *  A new instance of {@code AutoSaveTimer}.
   */
  public static AutoSaveTimer create(int delayMs) {
    return new AutoSaveTimer(delayMs);
  }

  private AutoSaveTimer(int delay) {
    super(delay, AutoSaveTimer::save);
  }

  private static void save(ActionEvent e) {
    ServiceRepository
      .sessionService
      .saveCurrentSession();
  }
}
