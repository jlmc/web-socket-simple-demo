package org.connect4.client.dtos;

/**
 * The Class User.
 */
public class User {

    /** The id. */
    private String id;

    /** The username. */
    private String username;

    /** The display name. */
    private String displayName;

    public User() {
        super();
    }

    /**
     * Gets the id.
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     * @param username
     *            the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the display name.
     * @return the display name
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Sets the display name.
     * @param displayName
     *            the new display name
     */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
}
