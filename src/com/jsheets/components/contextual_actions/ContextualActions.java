package com.jsheets.components.contextual_actions;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.jsheets.components.icons.FunctionIcon;
import com.jsheets.components.work_sheet.CellSelectionEvent;

public class ContextualActions extends JPanel {
  private final CellSpan selectedCell = new CellSpan();
  private final ExpressionField expression = new ExpressionField();

  public ContextualActions() {
    super();

    setLayout(new FlowLayout(FlowLayout.LEFT));

    add(selectedCell);
    add(new FunctionIcon());
    add(expression);
  }

  public void setSelectedCell(int[] rows, int[] cols) {
    selectedCell.updateText(rows, cols);
  }

  public void setExpression(CellSelectionEvent e) {
    expression.setText(e);
  }

  public void isExpressionEditable(boolean editable) {
    expression.setEditable(editable);
  }
}
