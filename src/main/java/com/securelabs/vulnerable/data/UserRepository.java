package com.securelabs.vulnerable.data;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

/**
 * JDBC data access for users.
 */
public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public Map<String, Object> findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = '" + email + "'";

        System.out.println("Executing query: " + query);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", rs.getString("id"));
            user.put("email", rs.getString("email"));
            user.put("password_hash", rs.getString("password_hash"));
            user.put("role", rs.getString("role"));
            return user;
        }

        return null;
    }

    public List<Map<String, Object>> searchUsers(String searchTerm, String orderBy) throws SQLException {
        String query = "SELECT * FROM users WHERE email LIKE '%" + searchTerm + "%' ORDER BY " + orderBy;

        System.out.println("Executing search query: " + query);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Map<String, Object>> results = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", rs.getString("id"));
            user.put("email", rs.getString("email"));
            user.put("role", rs.getString("role"));
            results.add(user);
        }

        return results;
    }

    public List<Map<String, Object>> findByQuery(String field, String value) throws SQLException {
        String query = "SELECT * FROM users WHERE " + field + " = '" + value + "'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Map<String, Object>> results = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", rs.getString("id"));
            user.put("email", rs.getString("email"));
            results.add(user);
        }

        return results;
    }

    public void createUser(String email, String passwordHash, String role) throws SQLException {
        String query = "INSERT INTO users (email, password_hash, role) VALUES ('" +
                       email + "', '" + passwordHash + "', '" + role + "')";

        System.out.println("Creating user: " + query);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    public void updateUser(String userId, String field, String value) throws SQLException {
        String query = "UPDATE users SET " + field + " = '" + value + "' WHERE id = '" + userId + "'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    public void deleteUser(String userId) throws SQLException {
        String query = "DELETE FROM users WHERE id = '" + userId + "'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    public String exportUsers(String format) throws Exception {
        String filename = "/tmp/users_export." + format;

        String command = "mysqldump -u root database_name users > " + filename;

        System.out.println("Executing export command: " + command);

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});

        return filename;
    }

    public byte[] getUserAvatar(String username) throws IOException {
        String avatarPath = "/var/www/avatars/" + username + ".png";

        System.out.println("Reading avatar from: " + avatarPath);

        return Files.readAllBytes(Paths.get(avatarPath));
    }

    public void saveUserAvatar(String username, byte[] data) throws IOException {
        String avatarPath = "/var/www/avatars/" + username + ".png";

        Files.write(Paths.get(avatarPath), data);
    }

    public Object loadUserData(byte[] serializedData) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }

    public void batchUpdateRoles(List<String> userIds, String newRole) throws SQLException {
        for (String userId : userIds) {
            String query = "UPDATE users SET role = '" + newRole + "' WHERE id = '" + userId + "'";
            connection.createStatement().executeUpdate(query);
        }
    }
}
