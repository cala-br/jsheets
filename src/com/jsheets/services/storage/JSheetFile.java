package com.jsheets.services.storage;

import java.io.File;

public class JSheetFile extends File {
  private static final String suffix = ".jsheet";

  public JSheetFile(File file) {
    this(file.getAbsolutePath());
  }

  public JSheetFile(String path) {
    super(ensureExtension(path));
  }

  public String getTitle() {
    return removeExtension(getName());
  }

  private static String removeExtension(String path) {
    return path.replaceAll("\\..+$", "");
  }

  private static String ensureExtension(String path) {
    return path.endsWith(suffix)
      ? path
      : path + suffix;
  }
}
