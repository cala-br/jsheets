package com.jsheets.frames;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import com.jsheets.components.contextual_actions.ContextualActions;
import com.jsheets.components.top_bar.TopBar;
import com.jsheets.components.work_sheet.RowHeader;
import com.jsheets.components.work_sheet.TableScrollPane;
import com.jsheets.components.work_sheet.WorkSheet;

public class MainFrame extends JFrame {
  private final ContextualActions actions = new ContextualActions();

  public MainFrame() {
    super();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setJMenuBar(new TopBar());

    add(actions, BorderLayout.NORTH);
    add(createTable(), BorderLayout.CENTER);
    pack();
  }


  private Container createTable() {
    return RowHeader.wrap(
      new TableScrollPane(
        new WorkSheet(e -> {
          actions.setSelectedCell(e.rows, e.columns);
          actions.isExpressionEditable(e.hasSingleCell);
          actions.setExpression(e);
        }), 15
      )
    );
  }
}
