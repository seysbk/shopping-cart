// Builds the clothing cards and handles add-to-cart actions on the home page.
package com.hellocode.shoppingcart.step2.home;

import com.hellocode.shoppingcart.step2.cart.CartService;
import com.hellocode.shoppingcart.step2.domain.Product;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.math.RoundingMode;

public class HomeController {

    @FXML
    private GridPane productGridPane;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        // Set up a simple 3-column grid each time the home view loads.
        productGridPane.getChildren().clear();
        productGridPane.getColumnConstraints().clear();

        for (int i = 0; i < 3; i++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(33.33);
            constraints.setHgrow(Priority.ALWAYS);
            productGridPane.getColumnConstraints().add(constraints);
        }

        int index = 0;
        for (Product product : Product.values()) {
            productGridPane.add(createProductCard(product), index % 3, index / 3);
            index++;
        }
    }

    private VBox createProductCard(Product product) {
        // One reusable card per product with image, name, price, and action button.
        Region imageBox = createImageBox(product);

        Label nameLabel = new Label(formatName(product));
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");

        Label priceLabel = new Label("GH\u20B5" + product.getPrice().setScale(2, RoundingMode.HALF_UP));
        priceLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #0f766e;");

        Button addButton = new Button("Add to cart");
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setStyle(
            "-fx-background-color: linear-gradient(to right, #2563eb, #1d4ed8);" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;"
        );
        addButton.setOnAction(event -> {
            CartService.getInstance().add(product);
            if (statusLabel != null) {
                // Immediate feedback so the user knows the click worked.
                statusLabel.setText(formatName(product) + " added to cart");
            }
        });

        VBox card = new VBox(12, imageBox, nameLabel, priceLabel, addButton);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(16));
        card.setPrefWidth(240);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #e5e7eb;" +
            "-fx-border-radius: 18;" +
            "-fx-effect: dropshadow(gaussian, rgba(15, 23, 42, 0.12), 18, 0.18, 0, 5);"
        );

        StackPane wrapper = new StackPane(card);
        wrapper.setPadding(new Insets(10));
        return new VBox(wrapper);
    }

    private Region createImageBox(Product product) {
        // Load the local image for this product, or show a placeholder if missing.
        ImageView imageView = new ImageView();
        try {
            Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
            imageView.setImage(image);
        } catch (Exception ignored) {
            // Fall back to a styled placeholder if an image is missing.
        }

        imageView.setFitWidth(140);
        imageView.setFitHeight(140);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        StackPane imageFrame = new StackPane(imageView);
        imageFrame.setPrefSize(170, 170);
        imageFrame.setMinSize(170, 170);
        imageFrame.setMaxSize(170, 170);
        imageFrame.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #f8fafc, #e2e8f0);" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 16;"
        );

        if (imageView.getImage() == null) {
            Label placeholder = new Label("Image");
            placeholder.setStyle("-fx-text-fill: #64748b; -fx-font-size: 15px;");
            imageFrame.getChildren().add(placeholder);
        }

        return imageFrame;
    }

    private String formatName(Product product) {
        String raw = product.name().toLowerCase();
        return Character.toUpperCase(raw.charAt(0)) + raw.substring(1);
    }
}
