package org.connect4.client.endpoint;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.MessageHandler;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
//import javax.websocket.MessageHandler;

import org.connect4.decoders.JsonMessageDecoder;
import org.connect4.encoder.JsonMessageEncoder;
import org.connect4.messages.ChatMessage;
import org.connect4.messages.Message;
import org.connect4.messages.MessageType;

//import javax.websocket.MessageHandler;

/**
 * The Class ConnectEndpoint.
 */
@ClientEndpoint(decoders = {JsonMessageDecoder.class }, encoders = {JsonMessageEncoder.class })
public class ConnectEndpoint {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ConnectEndpoint.class.getName());

    /** The user session. */
    private Session userSession;

    /** The message handler. */
    @SuppressWarnings("unused")
    private MessageHandler messageHandler;

    /**
     * The Constructor.
     */
    public ConnectEndpoint() {
        super();
    }

    /**
     * Connect.
     * @param uri
     *            the uri
     * @param onMessageHandler
     *            the message handler
     */
    public void connect(final URI uri, final MessageHandler onMessageHandler) {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            this.messageHandler = onMessageHandler;

            container.connectToServer(this, uri);

        } catch (DeploymentException | IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

    }

    /**
     * On open. Callback hook for connection open events
     * @param session
     *            the session which is opened
     */
    @OnOpen
    public void onOpen(final Session session) {
        LOGGER.info("connected.");

        this.userSession = session;
    }

    /**
     * On message.
     * @param message
     *            the message
     */
    @SuppressWarnings("static-method")
    @OnMessage
    public void onMessage(final Message message) {
        LOGGER.log(Level.FINE, message.getType().toString());

        if (MessageType.PUBLIC.equals(message.getType())) {
            final ChatMessage chatMessage = (ChatMessage) message;
            System.out.println(String.format("\n\nMSG REC: %s write:\n\t%s", chatMessage.getToken(), chatMessage.getMessage()));

            // this.messageHandler.notify();
        }
    }

    /**
     * Send message.
     * @param msg
     *            the msg
     * @param onMessageSendHandler
     *            the on message send handler
     */
    public void sendMessage(final Message msg) {
        if (this.userSession != null) {
            try {
                this.userSession.getBasicRemote().sendObject(msg);

                // onMessageSendHandler.sendHandler(OperationResult.OK);

            } catch (IOException | EncodeException e) {
                e.printStackTrace();

                // onMessageSendHandler.sendHandler(OperationResult.KO);
            }
        }
    }

    /**
     * On error.
     * @param t
     *            the t
     */
    @SuppressWarnings("static-method")
    @OnError
    public void onError(final Throwable t) {
        t.printStackTrace();
    }

}
