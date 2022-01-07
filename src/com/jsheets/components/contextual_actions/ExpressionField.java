package com.jsheets.components.contextual_actions;

import javax.swing.JTextField;

import com.jsheets.components.worksheet.CellSelectionEvent;

public class ExpressionField extends JTextField {
  public ExpressionField() {
    super();
  }

  public void setText(CellSelectionEvent e) {
    final var text = e.hasSingleCell
      ? e.data.get(0).getExpression()
      : e.data.onlyWithValue().toString();

    setText(text);
  }

  @Override
  public void setText(String value) {
    super.setText(value);
  }
}
