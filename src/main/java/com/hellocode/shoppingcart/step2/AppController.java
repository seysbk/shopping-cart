// Main shell controller that swaps the center view between home and cart.
package com.hellocode.shoppingcart.step2;

import com.hellocode.shoppingcart.BaseController;
import com.hellocode.shoppingcart.step2.cart.CartView;
import com.hellocode.shoppingcart.step2.home.HomeView;
import javafx.fxml.FXML;

import java.io.IOException;

public class AppController extends BaseController {

    @FXML
    public void initialize() {
        // Show the home page first when the shell loads.
        showHomeView();
    }

    public void showHomeView() {
        try {
            // Load the product grid into the center area.
            contentPane.setCenter(new HomeView().getView());
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load home view", exception);
        }
    }

    public void showCartView() {
        try {
            // Swap the center area to the cart page.
            contentPane.setCenter(new CartView().getView());
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load cart view", exception);
        }
    }
}
