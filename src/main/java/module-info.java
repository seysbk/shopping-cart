module com.hellocode.shoppingcart {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    exports com.hellocode.shoppingcart.step1;

    opens com.hellocode.shoppingcart.step1 to javafx.fxml;
    opens com.hellocode.shoppingcart.step2 to javafx.fxml;
    opens com.hellocode.shoppingcart.step2.home to javafx.fxml;
}