package com.jsheets.services.worksheet_manager;

import java.util.ArrayList;
import java.util.List;

import com.jsheets.components.work_sheet.WorkSheet;

public class WorksheetManagerService {
  private final List<WorkSheet> worksheets = new ArrayList<>();

  private int currentlyActive;


  public List<WorkSheet> getAll() {
    return worksheets;
  }

  public WorkSheet getCurrentlyActive() {
    return worksheets.get(currentlyActive);
  }


  public void register(WorkSheet w) {
    worksheets.add(w);
  }

  public void unregister(WorkSheet w) {
    worksheets.remove(w);
  }

  public void setActive(int index) {
    currentlyActive = index;
  }
}
