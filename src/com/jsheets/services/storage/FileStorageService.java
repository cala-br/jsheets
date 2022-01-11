package com.jsheets.services.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jsheets.components.cells.SerializableCell;

public class FileStorageService extends StorageService {
  @Override
  public void loadWorksheet(JSheetFile file) {
    try (
      final var fileIn = new FileInputStream(file);
      final var objIn = new ObjectInputStream(fileIn);
    ) {
      final var raw = objIn.readObject();

      if (raw instanceof SerializableCell[] cells) {
        onWorksheetLoaded.fire(
          new WorksheetLoadedEvent(file, cells)
        );
      }
    }
    catch (IOException | ClassNotFoundException e) {
      onException.fire(e);
    }
  }

  @Override
  public void saveWorksheet(JSheetFile file, SerializableCell[] cells) {
    try (
      final var fileOut = new FileOutputStream(file);
      final var objOut = new ObjectOutputStream(fileOut);
    ) {
      objOut.writeObject(cells);
      onWorksheetSaved.fire(
        new WorksheetSavedEvent(file)
      );
    }
    catch (IOException e) {
      onException.fire(e);
    }
  }
}
