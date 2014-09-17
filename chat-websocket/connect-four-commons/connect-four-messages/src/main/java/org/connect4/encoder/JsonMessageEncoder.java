package org.connect4.encoder;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.connect4.messages.Message;

/**
 * The Class JsonMessageEncoder.
 */
public class JsonMessageEncoder implements Encoder.Text<Message> {

    /*
     * (non-Javadoc)
     * @see javax.websocket.Encoder#init(javax.websocket.EndpointConfig)
     */
    @Override
    public void init(final EndpointConfig config) {
        // Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see javax.websocket.Encoder#destroy()
     */
    @Override
    public void destroy() {
        // Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see javax.websocket.Encoder.Text#encode(java.lang.Object)
     */
    @Override
    public String encode(final Message object) throws EncodeException {
        final StringWriter sWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(sWriter)) {
            final JsonObjectBuilder builder = Json.createObjectBuilder();

            // builder.add(MessageKeys.TYPE, String.valueOf(object.getType()));
            object.jsonBuilderAddProperties(builder);

            jsonWriter.writeObject(builder.build());

        }

        return sWriter.toString();
    }

}
