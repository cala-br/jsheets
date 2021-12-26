package com.jsheets.components.work_sheet;

import javax.swing.JTable;

import com.jsheets.model.WorkSheetModel;

public class WorkSheet extends JTable {
  public WorkSheet() {
    super(new WorkSheetModel());
  }
}
