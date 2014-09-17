package org.connect4.messages;

import javax.json.JsonObjectBuilder;

/**
 * The Class ChatMessage.
 */
public class ChatMessage extends Message {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The token. */
    private String token;

    /** The message. */
    private String message;

    /**
     * The Constructor.
     */
    public ChatMessage() {
        super(MessageType.PUBLIC);
    }

    /**
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the token.
     * @return the token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Sets the message.
     * @param message
     *            the message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Sets the token.
     * @param token
     *            the token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * Json builder.
     * @param builder
     *            the builder
     */
    @Override
    public void jsonBuilderAddProperties(final JsonObjectBuilder builder) {
        if (builder != null) {
            builder.add(MessageKeys.TYPE, String.valueOf(getType())).add(MessageKeys.TOKEN, String.valueOf(getToken()))
                    .add(MessageKeys.MESSAGE, String.valueOf(getMessage()));
        }
    }

}
