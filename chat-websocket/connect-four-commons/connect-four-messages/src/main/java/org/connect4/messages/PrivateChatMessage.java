package org.connect4.messages;

import javax.json.JsonObjectBuilder;

/**
 * The Class PrivateChatMessage.
 */
public class PrivateChatMessage extends Message {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The token. */
    private String token;

    /** The coversation id. */
    private String coversationId;

    /** The message. */
    private String message;

    /**
     * The Constructor.
     */
    public PrivateChatMessage() {
        super(MessageType.PRIVATE);
    }

    /**
     * Gets the coversation id.
     * @return the coversation id
     */
    public String getCoversationId() {
        return this.coversationId;
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
     * Sets the coversation id.
     * @param coversationId
     *            the coversation id
     */
    public void setCoversationId(final String coversationId) {
        this.coversationId = coversationId;
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

    @Override
    public void jsonBuilderAddProperties(final JsonObjectBuilder builder) {
        if (builder != null) {
            builder.add(MessageKeys.TYPE, String.valueOf(getType())).add(MessageKeys.TOKEN, getToken())
                    .add(MessageKeys.COVERSATIONID, this.coversationId).add(MessageKeys.MESSAGE, this.message);
        }

    }

}
