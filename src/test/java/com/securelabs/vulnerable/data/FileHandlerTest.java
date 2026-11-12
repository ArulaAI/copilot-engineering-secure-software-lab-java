package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Legitimate behavior that any fix must preserve. Runs in every build. The upload folder does
 * not exist on lab machines, so a normal name reaches the file system and reports a missing file
 * rather than being rejected as an attack.
 */
class FileHandlerTest {

    @Test
    void normalFileNameIsNotRejected() {
        assertThrows(NoSuchFileException.class, () -> new FileHandler().readFile("report.pdf"));
    }

    @Test
    void dotDotThatStaysInsideTheUploadFolderIsNotRejected() {
        assertThrows(NoSuchFileException.class,
            () -> new FileHandler().readFile("invoices/../report.pdf"));
    }
}
