package com.securelabs.vulnerable.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("1", new User("1", "admin@example.com", "admin", "password123"));
        users.put("2", new User("2", "user@example.com", "user", "userpass"));
    }

    @GetMapping
    public ResponseEntity<?> listUsers() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (User user : users.values()) {
            result.add(Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole(),
                "password", user.getPassword()
            ));
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        User user = users.get(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User " + userId + " does not exist"));
        }

        return ResponseEntity.ok(Map.of(
            "id", user.getId(),
            "email", user.getEmail(),
            "role", user.getRole(),
            "password", user.getPassword()
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getEmail().contains(query)) {
                results.add(Map.of(
                    "id", user.getId(),
                    "email", user.getEmail(),
                    "role", user.getRole()
                ));
            }
        }

        return ResponseEntity.ok(results);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody Map<String, Object> updates) {
        User user = users.get(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("role")) {
            user.setRole((String) updates.get("role"));
        }
        if (updates.containsKey("password")) {
            user.setPassword((String) updates.get("password"));
        }

        return ResponseEntity.ok(Map.of("message", "User updated", "user", Map.of(
            "id", user.getId(),
            "email", user.getEmail(),
            "role", user.getRole()
        )));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        User removed = users.remove(userId);

        if (removed == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        return ResponseEntity.ok(Map.of("deleted", true, "userId", userId));
    }

    @GetMapping("/debug/all")
    public ResponseEntity<?> debugAllUsers() {
        return ResponseEntity.ok(Map.of(
            "users", users,
            "count", users.size(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    @PostMapping("/admin/reset-passwords")
    public ResponseEntity<?> resetAllPasswords() {
        for (User user : users.values()) {
            user.setPassword("reset123");
        }

        return ResponseEntity.ok(Map.of("message", "All passwords reset to default"));
    }

    @PostMapping("/bulk-create")
    public ResponseEntity<?> bulkCreateUsers(@RequestBody List<Map<String, Object>> userList) {
        List<String> createdIds = new ArrayList<>();
        for (Map<String, Object> userData : userList) {
            String id = String.valueOf(users.size() + 1);
            User user = new User(
                id,
                (String) userData.get("email"),
                (String) userData.getOrDefault("role", "admin"),
                (String) userData.getOrDefault("password", "password")
            );
            users.put(id, user);
            createdIds.add(id);
        }

        return ResponseEntity.ok(Map.of("created", createdIds));
    }

    // User class
    static class User {
        private String id;
        private String email;
        private String role;
        private String password;

        public User(String id, String email, String role, String password) {
            this.id = id;
            this.email = email;
            this.role = role;
            this.password = password;
        }

        public String getId() { return id; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
