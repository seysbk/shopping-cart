// Shared controller behavior for the main shell, especially closing the app.
package com.hellocode.shoppingcart;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public abstract class BaseController {

    @FXML
    protected BorderPane contentPane;

    public void closeApp() {
        // Close the active window if it exists, otherwise exit JavaFX safely.
        if (BaseApp.getWindow() != null) {
            BaseApp.getWindow().close();
        } else {
            Platform.exit();
        }
    }
}
