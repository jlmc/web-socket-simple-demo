package org.connect4.client.desktop.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import org.connect4.client.dtos.User;

public class UserListCell extends ListCell<User> {
    /*
     * @Override
     * public void updateItem(final User item, final boolean empty) {
     * super.updateItem(item, empty);
     * setText(item == null ? "null" : item.getDisplayName());
     * setGraphic(null);
     * }
     */

    @Override
    public void updateItem(final User cache, final boolean empty) {
        super.updateItem(cache, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);

            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            final GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));

            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            // Label icon = new Label(GeocachingIcons.getIcon(cache).toString());
            // icon.setFont(Font.font("FontAwesome", FontWeight.BOLD, 24));
            // icon.getStyleClass().add("cache-list-icon");
            // grid.add(icon, 0, 0, 1, 2);

            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            final Label name = new Label(cache.getId());
            name.getStyleClass().add("cache-list-name");
            grid.add(name, 1, 0);

            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            final Label dt = new Label(cache.getUsername() + " / " + cache.getDisplayName());
            grid.add(dt, 1, 1);
            dt.getStyleClass().add("cache-list-dt");

            /*
             * if (CacheUtils.hasUserFoundCache(cache, new Long(3906456))) {
             * JavaFXUtils.addClasses(this, CACHE_LIST_FOUND_CLASS);
             * JavaFXUtils.removeClasses(this, CACHE_LIST_NOT_FOUND_CLASS);
             * } else {
             * JavaFXUtils.addClasses(this, CACHE_LIST_NOT_FOUND_CLASS);
             * JavaFXUtils.removeClasses(this, CACHE_LIST_FOUND_CLASS);
             * }
             */

            setGraphic(grid);
        }
    }
}
