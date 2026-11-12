package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * PATH-01 exploit tests. They fail on the unfixed code. Enabled with -Dsecurity.verify=true.
 * A fix must reject a path that leaves the upload folder with IllegalArgumentException or
 * SecurityException, before touching the file system.
 */
@EnabledIfSystemProperty(named = "security.verify", matches = "true")
class FileHandlerSecurityTest {

    private static void assertRejected(Executable call) {
        Throwable thrown = null;
        try {
            call.execute();
        } catch (Throwable t) {
            thrown = t;
        }
        if (thrown == null) {
            fail("a path outside the upload folder was accepted");
        }
        assertTrue(thrown instanceof IllegalArgumentException || thrown instanceof SecurityException,
            "the path reached the file system instead of being rejected: " + thrown);
    }

    @Test
    void readFileRejectsTraversal() {
        assertRejected(() -> new FileHandler().readFile("../../../etc/passwd"));
    }

    @Test
    void deleteFileRejectsTraversal() {
        assertRejected(() -> new FileHandler().deleteFile("../../../tmp/file-that-does-not-exist"));
    }

    @Test
    void listFilesRejectsTraversal() {
        assertRejected(() -> new FileHandler().listFiles("../../.."));
    }
}
