package org.connect4.client.desktop.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.connect4.client.dtos.User;

/**
 * The Class UserProperty.
 */
public class UserProperty {

    /** The user. */
    private final User user;

    /** The id. */
    private final StringProperty id = new SimpleStringProperty() {
        @SuppressWarnings("synthetic-access")
        @Override
        public void set(final String v) {
            super.set(v);
            UserProperty.this.getUser().setId(v);
        }
    };

    /** The username. */
    private final StringProperty username = new SimpleStringProperty() {
        @SuppressWarnings("synthetic-access")
        @Override
        public void set(final String v) {
            super.set(v);
            UserProperty.this.getUser().setUsername(v);
        }
    };

    /** The display name. */
    private final StringProperty displayName = new SimpleStringProperty() {
        @SuppressWarnings("synthetic-access")
        @Override
        public void set(final String v) {
            super.set(v);
            UserProperty.this.getUser().setDisplayName(v);
        }
    };

    /**
     * Instantiates a new user property.
     * @param user
     *            the user
     */
    public UserProperty(final User user) {
        this.user = user;
    }

    /**
     * Gets the user.
     * @return the user
     */
    private User getUser() {
        return this.user;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public String getId() {
        return this.id.get();
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return this.username.get();
    }

    /**
     * Gets the display name.
     * @return the display name
     */
    public String getDisplayName() {
        return this.displayName.get();
    }

    /**
     * Sets the id.
     * @param value
     *            the new id
     */
    public final void setId(final String value) {
        this.id.set(value);
    }

    /**
     * Sets the username.
     * @param value
     *            the new username
     */
    public final void setUsername(final String value) {
        this.username.set(value);
    }

    /**
     * Sets the display name.
     * @param value
     *            the new display name
     */
    public final void setDisplayName(final String value) {
        this.displayName.set(value);
    }

}
