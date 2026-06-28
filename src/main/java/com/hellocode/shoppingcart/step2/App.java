// Step 2 application entry point. It only points to the shell FXML file.
package com.hellocode.shoppingcart.step2;

import com.hellocode.shoppingcart.BaseApp;

/**
 * JavaFX App
 */
public class App extends BaseApp {

    @Override
    protected String getFxmlPath() {
        return "/com/hellocode/shoppingcart/step2/cart-ui.fxml";
    }

    public static void main(String[] args) {
        launch();
    }

}
