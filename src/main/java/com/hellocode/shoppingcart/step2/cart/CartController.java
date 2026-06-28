// Controls the cart page, including totals, quantity changes, and checkout confirmation.
package com.hellocode.shoppingcart.step2.cart;

import com.hellocode.shoppingcart.step2.domain.Product;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.math.RoundingMode;
import java.util.Optional;

public class CartController {

    @FXML
    private VBox cartItemsBox;

    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {
        // Draw the current cart state when the page opens.
        refreshCart();
    }

    @FXML
    public void checkout() {
        if (CartService.getInstance().getItems().isEmpty()) {
            Alert emptyCart = new Alert(Alert.AlertType.INFORMATION);
            emptyCart.setTitle("Cart");
            emptyCart.setHeaderText("Your cart is empty");
            emptyCart.setContentText("Add a few items before checking out.");
            emptyCart.showAndWait();
            return;
        }

        // Show the bill first so the user can confirm the purchase amount.
        String bill = "Your total bill is GH\u20B5" + CartService.getInstance().getTotal().setScale(2, RoundingMode.HALF_UP);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Purchase");
        confirm.setHeaderText("Review your bill");
        confirm.setContentText(bill + "\n\nDo you want to complete this purchase?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Thank the user, then clear the cart for the next purchase.
            Alert thanks = new Alert(Alert.AlertType.INFORMATION);
            thanks.setTitle("Purchase Complete");
            thanks.setHeaderText("Thank you for shopping at Coders Collection");
            thanks.setContentText(bill + "\n\nThank you for shopping at Coders Collection.");
            thanks.showAndWait();
            CartService.getInstance().clear();
            refreshCart();
        }
    }

    private void refreshCart() {
        // Rebuild the cart list from the shared cart service.
        cartItemsBox.getChildren().clear();

        if (CartService.getInstance().getItems().isEmpty()) {
            Label empty = new Label("No items in the cart yet.");
            empty.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 16px;");
            cartItemsBox.getChildren().add(empty);
        } else {
            for (Product product : CartService.getInstance().getItems().keySet()) {
                cartItemsBox.getChildren().add(createCartRow(product));
            }
        }

        totalLabel.setText("Total: GH\u20B5" + CartService.getInstance().getTotal().setScale(2, RoundingMode.HALF_UP));
    }

    private HBox createCartRow(Product product) {
        // Each row shows the item name, quantity controls, and the line total.
        Label name = new Label(formatName(product));
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label quantityLabel = new Label("Qty: " + CartService.getInstance().getQuantity(product));
        quantityLabel.setStyle("-fx-text-fill: #374151; -fx-font-weight: bold;");

        Label lineTotal = new Label("GH\u20B5" + product.getPrice()
            .multiply(java.math.BigDecimal.valueOf(CartService.getInstance().getQuantity(product)))
            .setScale(2, RoundingMode.HALF_UP));
        lineTotal.setStyle("-fx-text-fill: #2563eb; -fx-font-weight: bold;");

        Button minusButton = new Button("-");
        minusButton.setStyle(
            "-fx-background-color: #e5e7eb;" +
            "-fx-text-fill: #111827;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;"
        );
        minusButton.setOnAction(event -> {
            CartService.getInstance().decrement(product);
            refreshCart();
        });

        Button plusButton = new Button("+");
        plusButton.setStyle(
            "-fx-background-color: #dbeafe;" +
            "-fx-text-fill: #1d4ed8;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;"
        );
        plusButton.setOnAction(event -> {
            CartService.getInstance().increment(product);
            refreshCart();
        });

        HBox quantityBox = new HBox(8, minusButton, quantityLabel, plusButton);

        VBox details = new VBox(6, name, quantityBox, lineTotal);
        HBox.setHgrow(details, Priority.ALWAYS);

        HBox row = new HBox(12, details);
        row.setPadding(new Insets(14));
        row.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #e5e7eb;" +
            "-fx-border-radius: 12;"
        );
        return row;
    }

    private String formatName(Product product) {
        String raw = product.name().toLowerCase();
        return Character.toUpperCase(raw.charAt(0)) + raw.substring(1);
    }
}
