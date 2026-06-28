// Loads the cart screen FXML for the cart page.
package com.hellocode.shoppingcart.step2.cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class CartView {

    private final Parent view;

    public CartView() throws IOException {
        URL url = getClass().getResource("/com/hellocode/shoppingcart/step2/cart/cart-view.fxml");
        if (url == null) {
            throw new IOException("Unable to load FXML resource: /com/hellocode/shoppingcart/step2/cart/cart-view.fxml");
        }
        // Build the cart view from FXML and keep the root node handy.
        FXMLLoader loader = new FXMLLoader(url);
        this.view = loader.load();
    }

    public Parent getView() {
        return view;
    }
}
