package com.jsheets.services.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jsheets.components.cells.SerializableCell;

public class FileStorageService extends StorageService {
  @Override
  public void loadWorksheet(String path) {
    final var file = new File(path);
    try (
      final var fileIn = new FileInputStream(file);
      final var objIn = new ObjectInputStream(fileIn);
    ) {
      final var raw = objIn.readObject();

      if (raw instanceof SerializableCell[] cells) {
        onWorksheetLoaded.fire(
          new WorksheetLoadedEvent(file.getName(), cells)
        );
      }
    }
    catch (IOException | ClassNotFoundException e) {
      onException.fire(e);
    }
  }

  @Override
  public void saveWorksheet(String path, SerializableCell[] cells) {
    final var pathWithExtension = path + ".jsheet";
    final var file = new File(pathWithExtension);
    try (
      final var fileOut = new FileOutputStream(file);
      final var objOut = new ObjectOutputStream(fileOut);
    ) {
      objOut.writeObject(cells);
      onWorksheetSaved.fire("");
    }
    catch (IOException e) {
      onException.fire(e);
    }
  }
}
