// Shared JavaFX app bootstrap: loads the main shell, opens the window, and handles dragging.
package com.hellocode.shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public abstract class BaseApp extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private static Stage window;

    protected abstract String getFxmlPath();

    @Override
    public final void start(Stage stage) throws IOException {
        URL url = getClass().getResource(getFxmlPath());
        if (url == null) {
            throw new IOException("Unable to load FXML resource: " + getFxmlPath());
        }

        // Load the shell and make the window full-size and draggable.
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Coders Collection");
        stage.setScene(new Scene(root, Color.TRANSPARENT));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);
        stage.setMaximized(true);
        if (!stage.isMaximized()) {
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            double height = Screen.getPrimary().getVisualBounds().getHeight();
            stage.setWidth(width);
            stage.setHeight(height);
        }
        makeDraggable(root, stage);
        stage.show();

        window = stage;
    }

    public static Stage getWindow() {
        return window;
    }

    private void makeDraggable(Parent root, Stage stage) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
