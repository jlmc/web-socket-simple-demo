package org.connect4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.connect4.decoders.JsonMessageDecoder;
import org.connect4.encoder.JsonMessageEncoder;
import org.connect4.messages.ChatMessage;
import org.connect4.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConnectFourEndpoint.
 */
@ServerEndpoint(value = "/endpoint", decoders = {JsonMessageDecoder.class }, encoders = {JsonMessageEncoder.class })
public class ConnectFourEndpoint {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectFourEndpoint.class);

    /** The Constant Peers. */
    private static final Set<Session> PEERS = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * On close.
     * @param session
     *            the session
     */
    @SuppressWarnings("static-method")
    @OnClose
    public void onClose(final Session session) {
        if (session != null) {
            LOGGER.info("Close connection to: {0}", session.getUserProperties().get("id"));

            PEERS.remove(session);
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
        LOGGER.error(t.getMessage());
    }

    /**
     * On message.
     * @param session
     *            the session
     * @param message
     *            the message
     */

    @OnMessage
    public void onMessage(final Session session, final Message message) {
        // for now just send the mesagem to all on-line conncted users
        LOGGER.info("on Message :  ");
        if (message != null && message.getType() != null) {
            switch (message.getType()) {
            case PUBLIC:

                final ChatMessage chatMessage = (ChatMessage) message;

                if (chatMessage.getMessage() != null && !chatMessage.getMessage().isEmpty()) {
                    final ChatMessage serverChatMessage = new ChatMessage();
                    serverChatMessage.setToken(String.format("SERVER: %s", session.getId()));
                    serverChatMessage.setMessage(chatMessage.getMessage());

                    sendMessageToAll(serverChatMessage);
                }

                break;

            default:
                break;
            }
        }
    }

    /**
     * On open.
     * @param session
     *            the session
     */
    @OnOpen
    public void onOpen(final Session session) {
        LOGGER.info("Open connection to: {0}", session.getId());

        PEERS.add(session);
        final Integer id = Integer.valueOf(PEERS.size());

        final ChatMessage msg = new ChatMessage();
        msg.setMessage(String.format("[username] = [%d] is ONLINE", id));
        msg.setToken(session.getId());

        sendMessageToAll(msg);

    }

    /**
     * Send message to all.
     * @param msg
     *            the msg
     */
    @SuppressWarnings("static-method")
    private void sendMessageToAll(final Message msg) {
        if (PEERS != null && msg != null) {
            for (final Session peer : PEERS) {
                try {
                    peer.getBasicRemote().sendObject(msg);
                } catch (final Exception e) {
                    LOGGER.error(e.toString());
                    PEERS.remove(peer);
                }
            }
        }

    }

}
