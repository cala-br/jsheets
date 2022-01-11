package com.jsheets.services.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jsheets.cells.SerializableCell;

public class FileStorageService extends StorageService {
  @Override
  public void loadWorksheet(JSheetFile file) {
    try (
      final var fileIn = new FileInputStream(file);
      final var objIn = new ObjectInputStream(fileIn);
    ) {
      final var raw = objIn.readObject();

      if (raw instanceof SerializableCell[]) {
        onWorksheetLoaded.fire(
          new WorksheetLoadedEvent(this, file, (SerializableCell[])raw)
        );
      }
    }
    catch (IOException | ClassNotFoundException e) {
      onException.fire(
        new StorageExceptionEvent(this, e)
      );
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
        new WorksheetSavedEvent(this, file)
      );
    }
    catch (IOException e) {
      onException.fire(
        new StorageExceptionEvent(this, e)
      );
    }
  }
}
