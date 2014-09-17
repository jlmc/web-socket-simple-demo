package org.connect4.decoders;

import java.io.StringReader;
import java.security.InvalidParameterException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.connect4.messages.ChatMessage;
import org.connect4.messages.Message;
import org.connect4.messages.MessageKeys;
import org.connect4.messages.MessageType;

/**
 * The Class JsonMessageDecoder.
 */
public class JsonMessageDecoder implements Decoder.Text<Message> {

    /*
     * (non-Javadoc)
     * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
     */
    @Override
    public void init(final EndpointConfig config) {
        // Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see javax.websocket.Decoder#destroy()
     */
    @Override
    public void destroy() {
        // Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see javax.websocket.Decoder.Text#decode(java.lang.String)
     */
    @Override
    public Message decode(final String string) throws DecodeException {
        try {
            Message message = null;

            final JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();

            final String type = jsonObject.getString(MessageKeys.TYPE);
            final MessageType messageType = MessageType.valueOf(type);

            switch (messageType) {
            case PUBLIC:

                final ChatMessage chatMessage = new ChatMessage();
                final String token = jsonObject.getString(MessageKeys.TOKEN);
                final String msg = jsonObject.getString(MessageKeys.MESSAGE);
                chatMessage.setToken(token);
                chatMessage.setMessage(msg);

                message = chatMessage;
                break;
            default:
                throw new InvalidParameterException("no message type found");
            }

            return message;

        } catch (final Exception e) {
            throw new DecodeException("[JsonMessageDecoder]", String.format("can't decode message: %s \n\n caused by: %s", string, e.getMessage()));
        }
    }

    /**
     * Determine if the message can be converted into either one of Message types Instance.
     * @param s
     *            the s
     * @return true, if will decode
     */
    @Override
    public boolean willDecode(final String s) {
        try {

            // check if incomming message is valid JSON
            Json.createReader(new StringReader(s)).readObject();

            return true;
        } catch (final Exception e) {
            return false;
        }
    }

}
