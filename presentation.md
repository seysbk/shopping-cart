# Shopping Cart App Walkthrough

## What the app does
This is a small JavaFX clothing store called Coders Collection.

The flow is:
- the app opens in a full-size transparent shell,
- the brand wordmark appears on the home and cart pages,
- the home page shows clothing products in a grid,
- each product can be added to a cart,
- the cart page shows quantities, line totals, and the grand total,
- checkout asks for confirmation and then shows a thank-you message.

## Main flow
[`BaseApp`](src/main/java/com/hellocode/shoppingcart/BaseApp.java) handles startup.

It:
- loads the shell FXML,
- creates the main scene,
- opens a large desktop-sized window,
- makes the root draggable,
- stores the current window so the close button can work.

[`step2.App`](src/main/java/com/hellocode/shoppingcart/step2/App.java) is the only app entry point now.

[`AppController`](src/main/java/com/hellocode/shoppingcart/step2/AppController.java) swaps the center content between the home view and cart view. It loads the home page first.

## Home page logic
[`HomeView`](src/main/java/com/hellocode/shoppingcart/step2/home/HomeView.java) loads the home page FXML.

[`HomeController`](src/main/java/com/hellocode/shoppingcart/step2/home/HomeController.java) builds the product cards in code.

The controller:
- loops through the `Product` enum,
- creates one card for each item,
- loads each local image,
- adds an `Add to cart` button to each card,
- shows a short status message when an item is added.

[`Product`](src/main/java/com/hellocode/shoppingcart/step2/domain/Product.java) is the product catalog.

Each product stores:
- a local image path,
- a price.

## Cart logic
[`CartService`](src/main/java/com/hellocode/shoppingcart/step2/cart/CartService.java) holds the selected products and calculates the total.

The cart is stored as a product-to-quantity map, so the app can:
- increase quantity,
- decrease quantity,
- remove the item when the quantity reaches zero,
- calculate each line total and the grand total.

[`CartView`](src/main/java/com/hellocode/shoppingcart/step2/cart/CartView.java) loads the cart FXML.

[`CartController`](src/main/java/com/hellocode/shoppingcart/step2/cart/CartController.java) renders the cart and handles checkout.

It:
- shows each selected product once,
- shows the quantity controls,
- shows the line total for each product,
- shows the total in `GH₵`,
- handles checkout,
- shows the bill before purchase,
- shows a thank-you dialog that says thank you for shopping at Coders Collection,
- clears the cart after a successful checkout.

## UI structure
[`cart-ui.fxml`](src/main/resources/com/hellocode/shoppingcart/step2/cart-ui.fxml) is the shell layout with the sidebar, close button, and content area.

[`home-view.fxml`](src/main/resources/com/hellocode/shoppingcart/step2/home/home-view.fxml) is the home page container.

[`cart-view.fxml`](src/main/resources/com/hellocode/shoppingcart/step2/cart/cart-view.fxml) is the cart page container.

## How the pieces work together
1. The app starts in `step2.App`.
2. `BaseApp` loads the shell.
3. `AppController` loads the home page first.
4. `HomeController` builds the product cards from `Product`.
5. Clicking `Add to cart` stores the item in `CartService`.
6. Clicking the cart icon loads the cart view.
7. Clicking `+` or `-` changes the product quantity in the cart.
8. Clicking `Checkout` shows the bill and a confirmation prompt.
9. If the user confirms, the app shows a thank-you message and clears the cart.

## Simple design choices worth mentioning
- `CartService` keeps shared state simple.
- `Product` uses an enum, so the catalog is fixed and easy to explain.
- `BigDecimal` is used for prices instead of `float`.
- The product images are local files, so the UI does not depend on external links.
- Shared startup behavior lives in `BaseApp`, which removes duplicate code.

## talking points for a demo
- The app uses one shell and swaps views in the center.
- The home and cart pages share the same brand wordmark.
- The home page is generated from product data instead of hardcoded labels.
- The cart keeps quantity and total calculations in one shared service.
- Checkout shows a confirmation step and a thank-you modal.
