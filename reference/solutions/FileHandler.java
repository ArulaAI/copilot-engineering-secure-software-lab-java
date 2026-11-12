package com.securelabs.vulnerable.data;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/**
 * File storage and URL fetch helpers for uploads.
 */
public class FileHandler {
    private static final String UPLOAD_DIR = "/var/www/uploads/";
    private static final String[] BLOCKED_EXTENSIONS = {".exe", ".bat"};

    public byte[] readFile(String filename) throws IOException {
        return Files.readAllBytes(insideUploadDir(filename));
    }

    /**
     * PATH-01 reference fix: resolve against the upload folder, normalize, and require the
     * result to stay inside it. Path.startsWith compares whole path segments, so a sibling such
     * as /var/www/uploads-evil is rejected.
     */
    private static Path insideUploadDir(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Missing file name");
        }
        Path base = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        Path resolved = base.resolve(name).normalize();
        if (!resolved.startsWith(base)) {
            throw new SecurityException("Path leaves the upload folder");
        }
        return resolved;
    }

    public void writeFile(String filename, byte[] data) throws IOException {
        String fullPath = UPLOAD_DIR + filename;

        Files.write(Paths.get(fullPath), data);
    }

    public boolean isAllowedFileType(String filename) {
        for (String blocked : BLOCKED_EXTENSIONS) {
            if (filename.toLowerCase().endsWith(blocked)) {
                return false;
            }
        }
        return true;
    }

    public String getContentType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else if (filename.endsWith(".pdf")) {
            return "application/pdf";
        }
        return "application/octet-stream";
    }

    public byte[] fetchFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);

        System.out.println("Fetching URL: " + urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setFollowRedirects(true);

        try (InputStream is = connection.getInputStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    public String readUrlContent(String urlString) throws Exception {
        URL url = new URL(urlString);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    public List<String> listFiles(String directory) throws IOException {
        File dir = insideUploadDir(directory).toFile();
        String[] files = dir.list();

        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    public void deleteFile(String filename) throws IOException {
        Files.deleteIfExists(insideUploadDir(filename));
    }

    public void moveFile(String source, String destination) throws IOException {
        Files.move(Paths.get(UPLOAD_DIR + source), Paths.get(UPLOAD_DIR + destination));
    }

    public void extractZip(String zipPath, String destDir) throws Exception {
        java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(
            new FileInputStream(UPLOAD_DIR + zipPath)
        );

        java.util.zip.ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String filePath = destDir + "/" + entry.getName();

            System.out.println("Extracting: " + filePath);

            if (!entry.isDirectory()) {
                Files.copy(zis, Paths.get(filePath));
            }
        }
        zis.close();
    }

    public File createTempFile(String prefix, String suffix) throws IOException {
        String filename = prefix + System.currentTimeMillis() + suffix;
        return new File("/tmp/" + filename);
    }
}
