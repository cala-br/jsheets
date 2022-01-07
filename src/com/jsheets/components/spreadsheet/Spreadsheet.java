package com.jsheets.components.spreadsheet;

import java.awt.Container;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import com.jsheets.components.worksheet.RowHeader;
import com.jsheets.components.worksheet.TableScrollPane;
import com.jsheets.components.worksheet.Worksheet;
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


  public void add(Worksheet worksheet) {
    add("New*", worksheet);
  }

  public void add(String title, Worksheet worksheet) {
    ServiceRepository
      .worksheetManager
      .register(worksheet);

    addTab(title, wrapWorksheet(worksheet));
    refreshTabs();
  }

  private Container wrapWorksheet(Worksheet worksheet) {
    return RowHeader.wrap(
      new TableScrollPane(
        worksheet, 15
      )
    );
  }

  private void refreshTabs() {
    for (int i = 0; i < getTabCount(); i++) {
      setTabComponentAt(i, new CloseableTab(this, this::remove));
    }
  }
}
