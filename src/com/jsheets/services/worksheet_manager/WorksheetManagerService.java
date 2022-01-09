package com.jsheets.services.worksheet_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.jsheets.components.worksheet.Worksheet;

public class WorksheetManagerService {
  private final List<Worksheet> worksheets = new ArrayList<>();

  private int currentlyActive;


  public Stream<Worksheet> getAll() {
    return worksheets.stream();
  }

  public Worksheet getCurrentlyActive() {
    return worksheets.get(currentlyActive);
  }


  public void register(Worksheet w) {
    worksheets.add(w);
  }

  public void unregister(Worksheet w) {
    worksheets.remove(w);
  }

  public void setActive(int index) {
    currentlyActive = index;
  }
}
