package com.jsheets.model;

import java.util.stream.IntStream;

import javax.swing.table.DefaultTableModel;

public class WorkSheetModel extends DefaultTableModel {
  public WorkSheetModel() {
    super();

    final var columns = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    final var rows = 100;

    for (var column : columns) {
      addColumn(column);
    }

    IntStream
      .range(0, rows)
      .mapToObj(i -> new String[columns.length])
      .forEach(r -> addRow(r));
  }
}
