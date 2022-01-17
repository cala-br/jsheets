package com.jsheets.services.worksheet_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.jsheets.components.worksheet.Worksheet;

public class WorksheetManagerService {
  private final List<Worksheet> worksheets = new ArrayList<>();
  private int currentlyActive = -1;


  public synchronized Stream<Worksheet> getAll() {
    return worksheets.stream();
  }

  public synchronized Worksheet getAt(int index) {
    return worksheets.get(index);
  }

  public synchronized Worksheet getCurrentlyActive() {
    return worksheets.get(currentlyActive);
  }

  public synchronized int indexOf(Worksheet w) {
    return worksheets.indexOf(w);
  }


  public synchronized void register(Worksheet w) {
    worksheets.add(w);
  }

  public synchronized void unregister(Worksheet w) {
    worksheets.remove(w);
  }

  public synchronized void setActive(int index) {
    currentlyActive = index;
  }
}
