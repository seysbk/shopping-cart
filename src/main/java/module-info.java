// Module descriptor that opens the JavaFX controller packages for FXML injection.
module com.hellocode.shoppingcart {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    exports com.hellocode.shoppingcart.step2;

    opens com.hellocode.shoppingcart to javafx.fxml;
    opens com.hellocode.shoppingcart.step2 to javafx.fxml;
    opens com.hellocode.shoppingcart.step2.cart to javafx.fxml;
    opens com.hellocode.shoppingcart.step2.home to javafx.fxml;
}
