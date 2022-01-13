package com.jsheets.services.session;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

public class SessionService {
  private static final Path sessionPath = Path.of("./session.txt");
  private static final File sessionFile = sessionPath.toFile();


  public boolean anyInLastSession() {
    return readLastOpenedFiles().count() > 0;
  }

  public void tryLoadLastSession() {
    readLastOpenedFiles().forEach(f -> {
      ServiceRepository
        .storageService
        .loadWorksheet(f);
    });
  }

  public void saveCurrentSession() {
    try (final var out = new PrintStream(sessionFile);) {
      saveAll();
      out.println(
        String.join("\n", getOpenWorksheetPaths())
      );
    }
    catch (IOException e) {
      ;
    }
  }

  private Stream<JSheetFile> readLastOpenedFiles() {
    try {
      return Files
        .lines(sessionPath)
        .filter(l -> !l.isBlank())
        .map(l -> new File(l))
        .map(f -> new JSheetFile(f))
        .filter(f -> f.exists());
    }
    catch (IOException e) {
      return Stream.empty();
    }
  }

  private List<String> getOpenWorksheetPaths() {
    return ServiceRepository
      .worksheetManager
      .getAll()
      .map(w -> w.getFile())
      .map(f -> f.getAbsolutePath())
      .collect(Collectors.toList());
  }


  private static void saveAll() {
    ServiceRepository
      .worksheetManager
      .getAll()
      .forEach(ServiceRepository.storageService::saveWorksheet);
  }
}
