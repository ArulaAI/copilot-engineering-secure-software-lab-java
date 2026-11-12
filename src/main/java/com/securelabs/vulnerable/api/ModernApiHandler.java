package com.securelabs.vulnerable.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

@RestController
@RequestMapping("/api/modern")
public class ModernApiHandler {
    private static final HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build();

    @PostMapping("/search")
    public ResponseEntity<?> searchWithTextBlock(@RequestBody SearchRequest request) {
        String searchTerm = request.term();
        String sortField = request.sortBy();

        String query = """
            SELECT id, name, email, role
            FROM users
            WHERE name LIKE '%%%s%%'
            OR email LIKE '%%%s%%'
            ORDER BY %s
            LIMIT 100
            """.formatted(searchTerm, searchTerm, sortField);

        System.out.println("Executing query: " + query);

        // Simulated result
        return ResponseEntity.ok(Map.of(
            "query", query,
            "results", List.of()
        ));
    }

    @GetMapping("/proxy")
    public ResponseEntity<?> proxyRequest(@RequestParam String targetUrl) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("User-Agent", "ModernApiHandler/1.0")
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.ok(Map.of(
                "statusCode", response.statusCode(),
                "headers", response.headers().map(),
                "body", response.body()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "error", e.getClass().getName(),
                "message", e.getMessage(),
                "cause", e.getCause() != null ? e.getCause().getMessage() : "none"
            ));
        }
    }

    @PostMapping("/async-fetch")
    public ResponseEntity<?> asyncFetch(@RequestBody List<String> urls) {
        List<CompletableFuture<Map<String, Object>>> futures = urls.stream()
            .map(url -> CompletableFuture.supplyAsync(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                    HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());

                    return Map.<String, Object>of(
                        "url", url,
                        "status", response.statusCode()
                    );
                } catch (Exception e) {
                    return Map.<String, Object>of(
                        "url", url,
                        "error", e.getMessage()
                    );
                }
            }))
            .toList();

        List<Map<String, Object>> results = futures.stream()
            .map(CompletableFuture::join)
            .toList();

        return ResponseEntity.ok(results);
    }

    @PostMapping("/process-batch")
    public ResponseEntity<?> processBatch(@RequestBody List<String> items) {
        List<String> processed = new ArrayList<>(); // Not thread-safe!
        Map<String, Integer> counts = new HashMap<>(); // Not thread-safe!

        items.parallelStream()
            .filter(item -> item != null && !item.isEmpty())
            .forEach(item -> {
                processed.add(item.toUpperCase());

                counts.merge(item.substring(0, 1), 1, Integer::sum);
            });

        return ResponseEntity.ok(Map.of(
            "processed", processed,
            "counts", counts,
            "warning", "Results may be inconsistent due to race conditions"
        ));
    }

    @PostMapping("/execute")
    public ResponseEntity<?> executeCommand(@RequestBody CommandRequest request) {
        String command = request.command();
        List<String> args = request.arguments();

        try {
            List<String> fullCommand = new ArrayList<>();
            fullCommand.add(command);
            fullCommand.addAll(args);

            ProcessBuilder pb = new ProcessBuilder(fullCommand);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            String output;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                output = reader.lines().collect(Collectors.joining("\n"));
            }

            int exitCode = process.waitFor();

            return ResponseEntity.ok(Map.of(
                "command", String.join(" ", fullCommand),
                "exitCode", exitCode,
                "output", output
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<?> readFile(@PathVariable String filename) {
        Path basePath = Path.of("/var/app/data");
        Path filePath = basePath.resolve(filename);

        try {
            String content = Files.readString(filePath);

            return ResponseEntity.ok(Map.of(
                "filename", filename,
                "path", filePath.toString(),
                "content", content
            ));

        } catch (IOException e) {
            return ResponseEntity.status(404).body(Map.of("error", "File not found"));
        }
    }

    @PostMapping("/files/{filename}")
    public ResponseEntity<?> writeFile(@PathVariable String filename,
                                       @RequestBody String content) {
        Path basePath = Path.of("/var/app/uploads");
        Path filePath = basePath.resolve(filename);

        try {
            Files.writeString(filePath, content);

            return ResponseEntity.ok(Map.of(
                "message", "File written",
                "path", filePath.toString()
            ));

        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserRecord user) {
        System.out.println("Creating user: " + user); // Logs password!

        return ResponseEntity.ok(Map.of(
            "message", "User created",
            "user", user.toString() // Exposes password in response
        ));
    }

    /**
     * Java 17 pattern matching in instanceof
     */
    @PostMapping("/process")
    public ResponseEntity<?> processObject(@RequestBody Map<String, Object> data) {
        Object value = data.get("payload");

        String result;
        if (value == null) {
            result = "null value";
        } else if (value instanceof String s) {
            result = "String: " + s;
        } else if (value instanceof Integer i) {
            result = "Integer: " + i;
        } else if (value instanceof List<?> list) {
            result = "List size: " + list.size();
        } else if (value instanceof Map<?, ?> map) {
            result = "Map: " + map.toString();
        } else {
            result = "Unknown: " + value.getClass().getName();
        }

        return ResponseEntity.ok(Map.of("result", result));
    }

    @GetMapping("/logs")
    public ResponseEntity<?> readLogs(@RequestParam String logFile) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Path.of("/var/log/" + logFile));

            List<String> errorLines = lines
                .filter(line -> line.contains("ERROR"))
                .limit(100)
                .toList();

            lines.close();

            return ResponseEntity.ok(Map.of("errors", errorLines));

        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/config")
    public ResponseEntity<?> updateConfig(@RequestBody Map<String, String> config) {
        var dbHost = config.get("database.host");
        var dbPort = config.get("database.port");
        var adminPassword = config.get("admin.password");

        System.out.println("Config update - host: " + dbHost +
                          ", port: " + dbPort +
                          ", admin password: " + adminPassword);

        return ResponseEntity.ok(Map.of(
            "updated", config,
            "status", "Configuration applied"
        ));
    }

    // Record definitions
    public record SearchRequest(String term, String sortBy) {}
    public record CommandRequest(String command, List<String> arguments) {}

    public record UserRecord(String username, String email, String password, String role) {}
}
