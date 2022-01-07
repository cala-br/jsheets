package com.jsheets.components.contextual_actions;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.jsheets.components.icons.FunctionIcon;
import com.jsheets.components.work_sheet.CellSelectionEvent;

public class ContextualActions extends JPanel {
  private final SelectedCellsField selectedCell = new SelectedCellsField();
  private final ExpressionField expression = new ExpressionField();

  public ContextualActions() {
    super();

    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    add(selectedCell);
    add(Box.createRigidArea(new Dimension(8, 0)));
    add(new FunctionIcon());
    add(Box.createRigidArea(new Dimension(2, 0)));
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
