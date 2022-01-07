package com.jsheets.components.spreadsheet;

import java.awt.Container;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import com.jsheets.components.work_sheet.RowHeader;
import com.jsheets.components.work_sheet.TableScrollPane;
import com.jsheets.components.work_sheet.WorkSheet;
import com.jsheets.services.ServiceRepository;

public class Spreadsheet extends JTabbedPane {
  public Spreadsheet() {
    super();
    addChangeListener(this::onTabChanged);
  }


  private void onTabChanged(ChangeEvent e) {
    final var selectedIndex = getSelectedIndex();
    ServiceRepository
      .worksheetManager
      .setActive(selectedIndex);
  }


  public void add(WorkSheet worksheet) {
    add("New*", worksheet);
  }

  public void add(String title, WorkSheet worksheet) {
    ServiceRepository
      .worksheetManager
      .register(worksheet);

    addTab(title, wrapWorksheet(worksheet));
    refreshTabs();
  }

  private Container wrapWorksheet(WorkSheet worksheet) {
    return RowHeader.wrap(
      new TableScrollPane(
        worksheet, 15
      )
    );
  }

  private void refreshTabs() {
    for (int i = 0; i < getTabCount(); i++) {
      setTabComponentAt(i, new CloseableTab(this));
    }
  }
}
