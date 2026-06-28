package com.hellocode.shoppingcart.step1;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        java.net.URL url = getClass().getResource("cart-ui.fxml");
        if (url == null) {
            throw new IOException("Unable to load FXML resource: cart-ui.fxml");
        }

        Parent root = FXMLLoader.load(url);
        stage.setTitle("Shopping Cart App");
        stage.setScene(new Scene(root, Color.TRANSPARENT));

        // phase 2
        stage.initStyle(StageStyle.TRANSPARENT);
        makeDraggable(root);
        stage.show();

        window = stage;
    }

    public static void main(String[] args) {
        launch();
    }


    public static Stage getWindow() {
        return window;
    }

    private void makeDraggable(Parent root){
        root.setOnMousePressed( e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        root.setOnMouseDragged( e -> {
            window.setX( e.getScreenX() - xOffset);
            window.setY( e.getScreenY() - yOffset);
        });
    }

}