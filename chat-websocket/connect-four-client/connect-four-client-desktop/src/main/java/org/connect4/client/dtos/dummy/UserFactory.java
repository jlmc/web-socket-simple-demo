package org.connect4.client.dtos.dummy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.connect4.client.dtos.User;

/**
 * A factory for creating User objects.
 */
public class UserFactory {

    /** The Constant USERS. */
    private static final Set<User> USERS = Collections.synchronizedSet(new HashSet<User>());

    static {
        for (int i = 0; i < 25; i++) {
            final User user = new User();
            user.setId(String.format("id_%d", Integer.valueOf(i)));
            user.setUsername(String.format("username_%d", Integer.valueOf(i)));
            user.setDisplayName(String.format("displayName_%d", Integer.valueOf(i)));
            USERS.add(user);
        }
    }

    /**
     * Gets the users.
     * @return the users
     */
    public static Collection<User> getUsers() {
        return new HashSet<>(USERS);
    }

}
