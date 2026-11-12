package com.securelabs.vulnerable.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** SQLI-01 exploit tests. They fail on the unfixed code. Enabled with -Dsecurity.verify=true. */
@EnabledIfSystemProperty(named = "security.verify", matches = "true")
class UserRepositorySecurityTest {

    @Test
    void emailLookupCannotBeTurnedIntoAlwaysTrue() throws Exception {
        UserRepository repository = new UserRepository(TestDatabase.seeded());
        assertNull(repository.findByEmail("nobody@example.com' OR '1'='1"),
            "an injected OR clause returned a user");
    }

    @Test
    void searchTermCannotEscapeTheLikeClause() throws Exception {
        UserRepository repository = new UserRepository(TestDatabase.seeded());
        assertTrue(repository.searchUsers("zzz' OR '1'='1' --", "email").isEmpty(),
            "an injected search term returned every user");
    }

    @Test
    void sortColumnOutsideTheAllowedListIsRejected() throws Exception {
        UserRepository repository = new UserRepository(TestDatabase.seeded());
        assertThrows(IllegalArgumentException.class,
            () -> repository.searchUsers("example", "(SELECT password_hash FROM users WHERE id='1')"),
            "an injected ORDER BY expression was executed");
    }
}
