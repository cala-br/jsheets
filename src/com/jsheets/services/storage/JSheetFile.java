package com.jsheets.services.storage;

import java.io.File;

public class JSheetFile extends File {
  private static final String tmpPrefixPattern = "^(?<tmpprefix>tmp-[\\w-]+\\.)";
  private static final String extensionPattern = "(?<extension>\\.jsheet)$";
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

  public boolean isTemporary() {
    return getName().matches(
      String.format("%s.+", tmpPrefixPattern)
    );
  }

  private static String removeExtension(String path) {
    return path
      .replaceAll(extensionPattern, "")
      .replaceAll(tmpPrefixPattern, "");
  }

  private static String ensureExtension(String path) {
    return path.endsWith(suffix)
      ? path
      : path + suffix;
  }
}
