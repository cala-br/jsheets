package com.jsheets.services.worksheet_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.jsheets.components.worksheet.Worksheet;

/**
 * A service that tracks the {@link Worksheet} that
 * are currently inside the {@link Spreadsheet}.
 */
public class WorksheetManagerService {
  private final List<Worksheet> worksheets = new ArrayList<>();
  private int currentlyActive = -1;


  /**
   * Returns the tracked worksheets.
   * @return
   *  A stream of currently tracked sheets.
   */
  public synchronized Stream<Worksheet> getAll() {
    return worksheets.stream();
  }

  /**
   * @return
   *  The Worksheet at the given index.
   */
  public synchronized Worksheet getAt(int index) {
    return worksheets.get(index);
  }

  /**
   * Returns the currently active worksheet, meaning
   * the worskheet that is currently selected on the
   * spreadsheet.
   * 
   * @return
   *  The currently active worksheet.
   */
  public synchronized Worksheet getCurrentlyActive() {
    return worksheets.get(currentlyActive);
  }

  /**
   * Returns the index at which a registered worksheet
   * is located.
   * 
   * @return
   *  A positive index or -1 if the sheet is not
   *  registered.
   */
  public synchronized int indexOf(Worksheet w) {
    return worksheets.indexOf(w);
  }

  /**
   * Registers an open worksheet.
   * @param w The worksheet to register.
   */
  public synchronized void register(Worksheet w) {
    worksheets.add(w);
  }

  /**
   * Unregisters a closed worksheet.
   * @param w The worksheet to unregister.
   */
  public synchronized void unregister(Worksheet w) {
    worksheets.remove(w);
  }

  /**
   * Sets the worksheet that is currently being
   * displayed by the spreadsheet.
   * @param index
   *  The index of the worksheet that is being displayed.
   */
  public synchronized void setActive(int index) {
    currentlyActive = index;
  }
}
