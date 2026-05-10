package com.augusto.aiagentsbasics.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

@Component
public class NoteTool {

    @Value("${notes.path}")
    private String notesPath;

    @Tool("Save a note into a file. Use when user asks to write or save a text.")
    public String saveNote(String fileName, String content) {
        Path path = Path.of(notesPath, fileName);
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return "Note has been saved with name: " + fileName;
        } catch (IOException e) {
            return "Error while saving note: " + e.getMessage();
        }
    }

    @Tool("Read the content from a saved note. Use when user asks to read or see a note.")
    public String loadNote(String fileName) {
        Path path = Path.of(notesPath, fileName);
        if (!Files.exists(path)) {
            return "Note with name: " + fileName + ".txt does not exist";
        }
        try {
            return Files.readString(path);
        } catch (IOException e) {
            return "Error while reading note: " + e.getMessage();
        }
    }

    @Tool("List all saved notes. Use when user asks to see which notes exists.")
    public String listNotes() {
        Path path = Path.of(notesPath);
        try {
            Files.createDirectories(path);
            String notes = Files.list(path)
                    .map(filePath -> filePath.getFileName().toString())
                    .collect(Collectors.joining(", "));
            return notes.isEmpty() ? "No notes saved yet!" : "Saved notes: " + notes;
        } catch (IOException e) {
            return "Error while listing notes: " + e.getMessage();
        }

    }
}
