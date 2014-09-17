package org.connect4.messages;

import java.io.Serializable;
import java.security.InvalidParameterException;

import javax.json.JsonObjectBuilder;

/**
 * The Class Message.
 */
public abstract class Message implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The type. */
    private MessageType type;

    /**
     * The Constructor.
     */
    public Message() {
        this(MessageType.PUBLIC);
    }

    /**
     * The Constructor.
     * @param type
     *            the type
     */
    public Message(final MessageType type) {
        super();

        if (type == null) {
            throw new InvalidParameterException("the type can't be null");
        }

        setType(type);
    }

    /**
     * Gets the type.
     * @return the type
     */
    public MessageType getType() {
        return this.type;
    }

    /**
     * Sets the type.
     * @param type
     *            the type
     */
    public void setType(final MessageType type) {
        this.type = type;
    }

    /**
     * Adds a name/JsonString pair to the JSON object associated with this object builder.
     * If the object contains a mapping for the specified name,
     * this method replaces the old value with the specified value.
     * @param builder
     *            the builder
     */
    public abstract void jsonBuilderAddProperties(final JsonObjectBuilder builder);
}
