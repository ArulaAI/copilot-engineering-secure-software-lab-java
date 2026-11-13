package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/** Opt-in negative checks. Starter must fail; no host files are read or deleted. */
@EnabledIfSystemProperty(named = "security.verify", matches = "true")
class FileHandlerSecurityTest {
    @TempDir Path root;

    private FileHandler handler() throws Exception {
        return new FileHandler(Files.createDirectories(root.resolve("uploads")));
    }
    private static void assertRejected(Executable call) {
        Throwable thrown = null;
        try { call.execute(); } catch (Throwable t) { thrown = t; }
        assertNotNull(thrown, "a path outside the upload folder was accepted");
        assertTrue(thrown instanceof IllegalArgumentException || thrown instanceof SecurityException,
            "the path reached the file system instead of being rejected: " + thrown);
    }
    @Test void readFileRejectsTraversal() throws Exception {
        Path outside = Files.writeString(root.resolve("private.txt"), "private");
        FileHandler handler = handler();
        assertRejected(() -> handler.readFile("../private.txt"));
        assertEquals("private", Files.readString(outside));
    }
    @Test void deleteFileRejectsTraversal() throws Exception {
        Path outside = Files.writeString(root.resolve("private.txt"), "private");
        FileHandler handler = handler();
        assertRejected(() -> handler.deleteFile("../private.txt"));
        assertTrue(Files.exists(outside));
    }
    @Test void listFilesRejectsTraversal() throws Exception {
        FileHandler handler = handler();
        assertRejected(() -> handler.listFiles(".."));
    }
}
