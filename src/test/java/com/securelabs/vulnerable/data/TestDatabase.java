package com.securelabs.vulnerable.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

/** Synthetic in-memory database for UserRepository tests. No real data. */
final class TestDatabase {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private TestDatabase() {
    }

    static Connection seeded() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:h2:mem:users" + COUNTER.incrementAndGet() + ";DB_CLOSE_DELAY=-1");
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users (id VARCHAR(10), email VARCHAR(100), "
                + "password_hash VARCHAR(100), role VARCHAR(20))");
            statement.execute("INSERT INTO users VALUES "
                + "('1', 'admin@example.com', 'hash-a', 'admin'), "
                + "('2', 'user@example.com', 'hash-u', 'user')");
        }
        return connection;
    }
}
