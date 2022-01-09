package com.jsheets.services.storage;

import java.io.File;

public class JSheetPath {
  private static final String suffix = ".jsheet";
  private final File file;


  public JSheetPath(File file) {
    this(file.getAbsolutePath());
  }

  public JSheetPath(String path) {
    this.file = path.endsWith(suffix)
      ? new File(path)
      : new File(path + suffix);
  }

  public boolean exists() {
    return file.exists();
  }

  public String getPath() {
    return file.getAbsolutePath();
  }

  public String getTitle() {
    return removeExtension(file.getName());
  }

  private static String removeExtension(String path) {
    return path.replaceAll("\\..+$", "");
  }
}
