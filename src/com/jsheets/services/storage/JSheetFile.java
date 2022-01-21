package com.jsheets.services.storage;

import java.io.File;

/**
 * Represents the path of a {@code .jsheet} file.
 * It ensures that the correct extension is present.
 */
public class JSheetFile extends File {
  private static final String tmpPrefixPattern = "^(?<tmpprefix>tmp-[\\w-]+\\.)";
  private static final String extensionPattern = "(?<extension>\\.jsheet)$";
  private static final String suffix = ".jsheet";

  /**
   * Creates a new {@code JSheetFile} from the specified
   * {@code File}.
   */
  public JSheetFile(File file) {
    this(file.getAbsolutePath());
  }

  /**
   * Creates a new {@code JSheetFile} from the provided path.
   */
  public JSheetFile(String path) {
    super(ensureExtension(path));
  }

  /**
   * Gets the jsheet's title from the path.
   * <br>
   * E.g.
   *  {@code abc/mysheet.jsheet} will return {@code mysheet}.
   * 
   * @return
   *  The title of the {@code jsheet}.
   */
  public String getTitle() {
    return removeExtension(getName());
  }

  /**
   * Tells wether this is a temporary file.
   * @return
   *  {@code true} if this is a temporary
   *  file, {@code false} otherwise.
   */
  public boolean isTemporary() {
    return getName().matches(
      String.format("%s.+", tmpPrefixPattern)
    );
  }


  /**
   * Tries to create a temporary file, returning {@code null} if an
   * exception is raised.
   * 
   * @return
   *  Either a new {@code JSheetFile} or {@code null}.
   */
  public static JSheetFile maybeCreateTemporary() {
    try {
      return new JSheetFile(
        File.createTempFile("tmp-", ".new")
      );
    }
    catch (Exception e) {
      return null;
    }
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
