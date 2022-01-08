package com.jsheets.services.storage;

import java.io.File;

public class JSheetPath {
  private final File file;

  public JSheetPath(File file) {
    this.file = file;
  }

  public String getPath() {
    return file.exists()
      ? file.getAbsolutePath()
      : file.getAbsolutePath() + ".jsheet";
  }

  public String getTitle() {
    return removeExtension(file.getName());
  }

  private static String removeExtension(String path) {
    return path.replaceAll("\\..+$", "");
  }
}
