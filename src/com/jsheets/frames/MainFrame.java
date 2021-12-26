package com.jsheets.frames;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import com.jsheets.components.top_bar.TopBar;
import com.jsheets.components.work_sheet.RowHeader;
import com.jsheets.components.work_sheet.TableScrollPane;
import com.jsheets.components.work_sheet.WorkSheet;

public class MainFrame extends JFrame {
  public MainFrame() {
    super();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setJMenuBar(new TopBar());

    add(createTable(), BorderLayout.CENTER);
    pack();
  }


  private Container createTable() {
    return RowHeader.wrap(
      new TableScrollPane(
        new WorkSheet(), 15
      )
    );
  }
}
