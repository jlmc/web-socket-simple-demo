package org.connect4.client.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.MessageHandler;

import org.connect4.client.endpoint.ConnectEndpoint;
import org.connect4.client.endpoint.handlers.OnMessageSend;
import org.connect4.client.endpoint.handlers.OperationResult;
import org.connect4.messages.ChatMessage;

/**
 * Hello world!.
 */
public class App implements MessageHandler.Whole<String>, OnMessageSend {

    /** The Constant SERVER_ENDPOINT_URL. */
    private static final String SERVER_ENDPOINT_URL = "wss://localhost:8080/connect4/endpoint";

    /**
     * The main method.
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        System.out.println("Hello World!");

        final App app = new App();

        final ConnectEndpoint client = new ConnectEndpoint();

        try {
            client.connect(new URI(SERVER_ENDPOINT_URL), app);
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }

        while (true) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            try {
                msg = br.readLine();

                final ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessage(msg);

                client.sendMessage(chatMessage);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessage(final String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendHandler(final OperationResult result) {
        // TODO Auto-generated method stub

    }
}
