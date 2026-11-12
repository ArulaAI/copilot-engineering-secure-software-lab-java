package com.securelabs.vulnerable.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ResourceController {
    private static final Map<String, Resource> resources = new HashMap<>();

    static {
        resources.put("res-1", new Resource("res-1", "user-1", "Resource 1 data"));
        resources.put("res-2", new Resource("res-2", "user-2", "Resource 2 data"));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<?> getResource(@PathVariable String resourceId) {
        Resource resource = resources.get(resourceId);

        if (resource == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Resource not found"));
        }

        return ResponseEntity.ok(Map.of(
            "id", resource.getId(),
            "ownerId", resource.getOwnerId(),
            "data", resource.getData()
        ));
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchExternalResource(@RequestParam String url) {
        System.out.println("Fetching URL: " + url);

        try {
            URL targetUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setInstanceFollowRedirects(true);

            int responseCode = connection.getResponseCode();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return ResponseEntity.ok(Map.of(
                    "url", url,
                    "statusCode", responseCode,
                    "content", response.toString()
                ));
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "error", "Failed to fetch URL",
                "details", e.getMessage(),
                "stackTrace", Arrays.toString(e.getStackTrace())
            ));
        }
    }

    @PostMapping("/proxy")
    public ResponseEntity<?> proxyRequest(@RequestBody Map<String, String> request) {
        String targetUrl = request.get("url");
        String method = request.getOrDefault("method", "GET");

        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            if (request.containsKey("headers")) {
            }

            int responseCode = connection.getResponseCode();
            return ResponseEntity.ok(Map.of("statusCode", responseCode));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/redirect")
    public void handleRedirect(@RequestParam String returnUrl, HttpServletResponse response) throws IOException {
        System.out.println("Redirecting to: " + returnUrl);

        response.sendRedirect(returnUrl);
    }

    @PutMapping("/{resourceId}/permissions")
    public ResponseEntity<?> updatePermissions(@PathVariable String resourceId,
                                               @RequestBody Map<String, Object> permissions) {
        Resource resource = resources.get(resourceId);
        if (resource == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Resource not found"));
        }

        resource.setPermissions(permissions);

        return ResponseEntity.ok(Map.of("message", "Permissions updated"));
    }

    @GetMapping("/debug")
    public ResponseEntity<?> debugInfo(HttpServletRequest request) {
        Map<String, Object> debug = new HashMap<>();
        debug.put("resources", resources);
        debug.put("environment", System.getenv());
        debug.put("properties", System.getProperties());
        debug.put("clientIp", request.getRemoteAddr());
        debug.put("headers", Collections.list(request.getHeaderNames()));

        return ResponseEntity.ok(debug);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable String resourceId) {
        Resource removed = resources.remove(resourceId);

        if (removed == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Resource not found"));
        }

        return ResponseEntity.ok(Map.of("deleted", true));
    }

    @GetMapping("/include")
    public ResponseEntity<?> includeFile(@RequestParam String file) {
        try {
            if (file.startsWith("http")) {
                URL url = new URL(file);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    return ResponseEntity.ok(Map.of("content", content.toString()));
                }
            } else {
                return ResponseEntity.ok(Map.of("content",
                    new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(file)))));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // Resource class
    static class Resource {
        private String id;
        private String ownerId;
        private String data;
        private Map<String, Object> permissions;

        public Resource(String id, String ownerId, String data) {
            this.id = id;
            this.ownerId = ownerId;
            this.data = data;
            this.permissions = new HashMap<>();
        }

        public String getId() { return id; }
        public String getOwnerId() { return ownerId; }
        public String getData() { return data; }
        public Map<String, Object> getPermissions() { return permissions; }
        public void setPermissions(Map<String, Object> permissions) { this.permissions = permissions; }
    }
}
