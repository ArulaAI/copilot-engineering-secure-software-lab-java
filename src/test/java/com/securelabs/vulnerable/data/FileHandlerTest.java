package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/** Legitimate file behavior must survive any security fix. All files are disposable. */
class FileHandlerTest {
    @TempDir Path uploads;

    private FileHandler handler() {
        return new FileHandler(uploads);
    }

    @Test void normalFileCanBeRead() throws Exception {
        Files.writeString(uploads.resolve("report.pdf"), "report");
        assertEquals("report", new String(handler().readFile("report.pdf")));
    }
    @Test void dotDotThatStaysInsideCanBeRead() throws Exception {
        Files.createDirectory(uploads.resolve("invoices"));
        Files.writeString(uploads.resolve("report.pdf"), "report");
        assertEquals("report", new String(handler().readFile("invoices/../report.pdf")));
    }
    @Test void directoryContentsCanBeListed() throws Exception {
        Path folder = Files.createDirectory(uploads.resolve("invoices"));
        Files.writeString(folder.resolve("invoice.txt"), "invoice");
        assertEquals(List.of("invoice.txt"), handler().listFiles("invoices"));
    }
    @Test void emptyDirectoryCanBeListed() throws Exception {
        Files.createDirectory(uploads.resolve("empty"));
        assertTrue(handler().listFiles("empty").isEmpty());
    }
    @Test void existingFileCanBeDeleted() throws Exception {
        Path file = Files.writeString(uploads.resolve("old.txt"), "old");
        handler().deleteFile("old.txt");
        assertFalse(Files.exists(file));
    }
    @Test void deletingMissingFileIsIdempotent() {
        assertDoesNotThrow(() -> handler().deleteFile("missing.txt"));
    }
    @Test void fileCanBeCreatedThroughTheHandler() throws Exception {
        FileHandler handler = handler();
        handler.writeFile("new.txt", "new".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertEquals("new", new String(handler.readFile("new.txt"), java.nio.charset.StandardCharsets.UTF_8));
    }
    @Test void existingFileCanBeOverwrittenThroughTheHandler() throws Exception {
        FileHandler handler = handler();
        handler.writeFile("update.txt", "before".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        handler.writeFile("update.txt", "after".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertEquals("after", new String(handler.readFile("update.txt"), java.nio.charset.StandardCharsets.UTF_8));
    }
}
