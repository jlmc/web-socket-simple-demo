package org.connect4.client.desktop.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Main extends Application {
    @Override
    public void start(final Stage primaryStage) {
        try {

            System.out.println(getClass().getResource("GeneralChatView.fxml"));

            final AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("GeneralChatView.fxml"));
            final Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
