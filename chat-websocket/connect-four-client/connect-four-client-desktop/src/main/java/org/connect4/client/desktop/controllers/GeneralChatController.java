package org.connect4.client.desktop.controllers;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;

import org.connect4.client.dtos.User;
import org.connect4.client.dtos.dummy.UserFactory;

public class GeneralChatController {
    /** The resources. ResourceBundle that was given to the FXMLLoader */

    @FXML
    private ResourceBundle resources;

    /** The location. URL location of the FXML file that was given to the FXMLLoader */
    @FXML
    private URL location;

    /** The online users list. fx:id="onlineUsersList" */
    @FXML
    private ListView<User> onlineUsersList; // Value injected by FXMLLoader

    /** The general history text area. fx:id="generalHistoryTextArea" */
    @FXML
    private TextArea generalHistoryTextArea;

    /** The edit text area. fx:id="editTextArea" */
    @FXML
    private TextArea editTextArea;

    private static final String imageUrl = "org/connect4/client/desktop/images/1.png";
    private static final Image image = new Image(GeneralChatController.class.getResource("/org/connect4/client/desktop/images/2.png").toExternalForm());

    //

    /**
     * Submit button. Handler for Button[id="sendButton"] onAction
     * @param event
     *            the event
     */
    @FXML
    void submitButton(final ActionEvent event) {
        if (this.editTextArea != null && this.editTextArea.getText() != null && !this.editTextArea.getText().isEmpty()) {
            if (this.generalHistoryTextArea.getText() == null) {
                this.generalHistoryTextArea.setText("" + this.editTextArea.getText());
                this.editTextArea.setText("");
            } else {
                this.generalHistoryTextArea.appendText("\n\n" + this.editTextArea.getText());
                this.editTextArea.setText("");
            }
        }
        System.out.println("call submitButton event ");

    }

    @FXML
    void selectUser(final MouseEvent mouseEvent) {
        // System.out.println("selected  User");

        if (mouseEvent.getClickCount() == 2 && this.onlineUsersList.getSelectionModel().getSelectedItem() != null) {
            final User user = this.onlineUsersList.getSelectionModel().getSelectedItem();
            System.out.println("selected: " + user.getDisplayName());
        }

    }

    final ObservableList<User> items = FXCollections.observableArrayList();

    /**
     * Initialize. This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert this.onlineUsersList != null : "fx:id=\"onlineUsersList\" was not injected: check your FXML file 'ViewTest.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

        final Collection<User> users = UserFactory.getUsers();
        for (final User user : users) {
            this.items.add(user);
        }
        this.onlineUsersList.setItems(this.items);

        this.onlineUsersList.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(final ListView<User> param) {

                // final Label leadLbl = new Label();
                // final Tooltip tooltip = new Tooltip();
                // ---------------------------------------

                final ListCell<User> cell = new ListCell<User>() {
                    @Override
                    public void updateItem(final User item, final boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            final HBox box = new HBox();
                            final StackPane pane = new StackPane();
                            final ImageView r = new ImageView();
                            final Rectangle rect = new Rectangle();
                            r.setImage(image);
                            r.setFitHeight(50);
                            r.setPreserveRatio(true);
                            r.setFitWidth(50);
                            rect.setWidth(50);
                            rect.setHeight(50);
                            pane.getChildren().addAll(rect, r);
                            final Text t = new Text(item.getDisplayName());
                            box.getChildren().addAll(pane, t);
                            box.setSpacing(10);
                            box.setPadding(new Insets(5, 5, 5, 5));
                            setGraphic(box);
                            // --------------------------------------------------

                            // leadLbl.setText(item.getDisplayName());
                            // setText(item.getId() + " " + item.getUsername());
                            // tooltip.setText(item.getDisplayName());
                            // setTooltip(tooltip);
                        }
                    }
                }; // ListCell
                return cell;
            }
        }); // setCellFactory

    }
}
