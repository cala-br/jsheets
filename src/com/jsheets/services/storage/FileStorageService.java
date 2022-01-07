package com.jsheets.services.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.function.Consumer;

import com.jsheets.components.cells.SerializableCell;

public class FileStorageService extends StorageService {
  @Override
  public void loadWorksheet(URI path) {
    openRead(path, in -> {
      try {
        final var raw = in.readObject();

        if (raw instanceof SerializableCell[] cells) {
          onWorksheetLoaded.fire(
            new WorksheetLoadedEvent(cells)
          );
        }
      }
      catch (IOException e) {

      }
      catch (ClassNotFoundException e) {

      }
    });
  }

  @Override
  public void saveWorksheet(URI path, SerializableCell[] cells) {
    openWrite(path, out -> {
      try {
        out.writeObject(cells);
        onWorksheetSaved.fire("");
      }
      catch (IOException e) {

      }
    });
  }

  private static void openRead(URI path, Consumer<ObjectInputStream> consumer) {
    final var file = new File(path);
    try (
      final var fileIn = new FileInputStream(file);
      final var objIn = new ObjectInputStream(fileIn);
    ) {
      consumer.accept(objIn);
    }
    catch (IOException e) {

    }
  }

  private static void openWrite(URI path, Consumer<ObjectOutputStream> consumer) {
    final var file = new File(path);
    try (
      final var fileOut = new FileOutputStream(file);
      final var objOut = new ObjectOutputStream(fileOut);
    ) {
      consumer.accept(objOut);
    }
    catch (IOException e) {

    }
  }
}
