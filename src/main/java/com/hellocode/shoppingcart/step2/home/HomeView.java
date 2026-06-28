// Loads the home screen FXML that shows the product grid.
package com.hellocode.shoppingcart.step2.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;

public class HomeView {

    private Parent view;

    public HomeView() throws IOException {
        URL url = getClass().getResource("/com/hellocode/shoppingcart/step2/home/home-view.fxml");
        if (url == null) {
            throw new IOException("Unable to load FXML resource: /com/hellocode/shoppingcart/step2/home/home-view.fxml");
        }
        // Build the home view once and keep the root node for reuse.
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        this.view = fxmlLoader.load();
    }

    public Parent getView(){
        return this.view;
    }
}
