package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** Legitimate behavior that any fix must preserve. Runs in every build. */
class UserRepositoryTest {

    @Test
    void findByEmailReturnsTheMatchingUser() throws Exception {
        UserRepository repository = new UserRepository(TestDatabase.seeded());
        Map<String, Object> user = repository.findByEmail("user@example.com");
        assertNotNull(user);
        assertEquals("2", user.get("id"));
    }

    @Test
    void searchUsersFiltersAndSortsByAnAllowedColumn() throws Exception {
        UserRepository repository = new UserRepository(TestDatabase.seeded());
        List<Map<String, Object>> users = repository.searchUsers("example", "email");
        assertEquals(List.of("admin@example.com", "user@example.com"),
            users.stream().map(u -> u.get("email")).toList());
    }
}
