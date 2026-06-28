# OOP and JavaFX Presentation Notes for the Shopping Cart App

## 1. What this project is about
This project is a beginner-friendly JavaFX application that shows how object-oriented principles can be used to build a simple shopping cart UI.

The app has two main screens:
- A home view where product names are displayed.
- A cart view where items can later be shown.

The main goal of this project is not only to make a UI, but also to show how classes, objects, data modeling, and event-driven programming work together.

---

## 2. OOP concepts used in this project

### 2.1 Classes and objects
A class is a blueprint. An object is a real instance created from that blueprint.

In this project:
- `App` is a class that starts the JavaFX application.
- `AppController` is a class that controls what happens in the UI.
- `HomeController` is a class that manages the home screen.
- `HomeView` is a class that loads the FXML view.

Example:
- `new HomeView()` creates an object of the `HomeView` class.
- `new Label("Apple")` creates a label object that is added to the screen.

### 2.2 Encapsulation
Encapsulation means hiding data inside a class and exposing it through methods.

In the `Product` enum, the fields `imageFile` and `price` are private. They are accessed through getters like `getImageFile()` and `getPrice()`.

This is good because:
- the data is protected,
- the class controls how the data is read,
- other classes do not change it directly.

### 2.3 Abstraction
Abstraction means showing only the important functionality and hiding the internal complexity.

For example:
- the user only sees buttons, labels, and products,
- but the controller classes handle the logic behind the scenes.

### 2.4 Separation of concerns
Each class has one main responsibility.

- `App` starts the application.
- `AppController` handles window actions such as closing the app and switching views.
- `HomeController` prepares the home screen content.
- `HomeView` loads the FXML file.
- `Product` stores product information.

This makes the code easier to read and maintain.

### 2.5 Association
Association means one object uses another object.

For example:
- `HomeController` uses the `Product` enum to display products.
- `AppController` uses `HomeView` to show the home page in the main layout.

### 2.6 Enum as a special data type
The `Product` enum is a special type used when a variable can only take a fixed set of values.

Instead of creating many separate classes for products, we use one enum to represent a small set of known products.

---

## 3. Code walkthrough from top to bottom

### 3.1 [src/main/java/com/hellocode/shoppingcart/step2/App.java](src/main/java/com/hellocode/shoppingcart/step2/App.java)
This is the entry point of the application.

It does three important things:
1. Loads the main FXML file.
2. Creates the JavaFX window and scene.
3. Shows the app on the screen.

Key ideas:
- `Application` is the base class for JavaFX apps.
- `start(...)` is the method that runs when the app begins.
- `Stage` is the main window.
- `Scene` is the content inside the window.

### 3.2 [src/main/java/com/hellocode/shoppingcart/step2/AppController.java](src/main/java/com/hellocode/shoppingcart/step2/AppController.java)
This class acts as the controller for the main layout.

It contains methods such as:
- `closeApp()` to close the window,
- `showHomeView()` to display the home view,
- `showCartView()` to display the cart view.

This is a good example of using one controller to manage the main user interface.

### 3.3 [src/main/java/com/hellocode/shoppingcart/step2/home/HomeView.java](src/main/java/com/hellocode/shoppingcart/step2/home/HomeView.java)
This class is responsible for loading an FXML file.

It separates the visual design from the logic.

So instead of creating UI elements directly in Java code, the app uses FXML to define the structure, while Java classes handle the behavior.

### 3.4 [src/main/java/com/hellocode/shoppingcart/step2/home/HomeController.java](src/main/java/com/hellocode/shoppingcart/step2/home/HomeController.java)
This class controls the home screen.

It uses `@FXML` to connect Java code to the FXML file. The `initialize()` method runs when the view is created. In that method, the app creates labels for several products and adds them to the grid.

This shows two important OOP ideas:
- object creation,
- event-driven initialization.

### 3.5 [src/main/java/com/hellocode/shoppingcart/step2/domain/Product.java](src/main/java/com/hellocode/shoppingcart/step2/domain/Product.java)
This class uses an enum to represent products.

Each product has:
- an image file name,
- a price.

This is a good example of modeling real-world entities as data objects.

---

## 4. JavaFX fundamentals explained simply
JavaFX is a framework used to build desktop applications with graphical user interfaces.

### 4.1 `Stage`
A `Stage` is the window of the application.

Think of it as the whole application window.

### 4.2 `Scene`
A `Scene` is the content shown inside the window.

A window can have many scenes, but in this app, one scene is used.

### 4.3 `Parent`
`Parent` is the base class for all visual UI nodes such as layouts and controls.

In this project, the root of the UI is loaded from FXML and treated as a `Parent`.

### 4.4 FXML
FXML is a declarative way of designing the UI.

Instead of writing every UI component in Java code, the layout is written in XML-like syntax.

This is helpful because:
- designers can understand the layout,
- developers can separate UI design from logic,
- the code becomes more organized.

### 4.5 Controllers
A controller is a Java class that handles events and logic for an FXML screen.

So the FXML file describes the visual structure, while the controller provides the behavior.

### 4.6 Event handling
When a user clicks something, JavaFX runs an event handler.

For example, clicking a button or region can call a method in the controller.

---

## 5. How to add a new cart item and show it in the cart view
The current app is a starter version, so the cart view is still simple. The following steps show how to extend it properly.

### Step 1: Add a new product in the enum
Open [src/main/java/com/hellocode/shoppingcart/step2/domain/Product.java](src/main/java/com/hellocode/shoppingcart/step2/domain/Product.java) and add a new product constant.

Example:
```java
public enum Product {
    APPLE("apple.jpg", 0.55f),
    MILK("milk.jpg", 0.78f),
    JUICE("juice.jpg", 0.56f),
    LETTUCE("lettuce.jpg", 0.78f),
    BREAD("bread.jpg", 1.20f),
    EMPTY("", 0.0f);
}
```

### Step 2: Create a visual item in the home view
Open [src/main/java/com/hellocode/shoppingcart/step2/home/HomeController.java](src/main/java/com/hellocode/shoppingcart/step2/home/HomeController.java) and add a new label for the product.

Example:
```java
Label breadLabel = new Label(Product.BREAD.name());
breadLabel.setPadding(new Insets(5, 5, 5, 5));
productGridPane.add(breadLabel, 3, 0);
```

### Step 3: Add an action to add the item to a cart list
A simple way to do this is to keep a list of selected products in the controller.

You can create a list like this:
```java
private final List<Product> cartItems = new ArrayList<>();

public void addToCart(Product product) {
    cartItems.add(product);
}
```

Then, when the user clicks a button for a product, call `addToCart(Product.BREAD);`.

### Step 4: Display the cart items in the cart view
Update [src/main/java/com/hellocode/shoppingcart/step2/AppController.java](src/main/java/com/hellocode/shoppingcart/step2/AppController.java) so that `showCartView()` creates a visible list of items.

Example idea:
```java
public void showCartView() {
    VBox cartBox = new VBox();
    cartBox.setSpacing(10);

    for (Product product : cartItems) {
        cartBox.getChildren().add(new Label(product.name()));
    }

    contentPane.setCenter(cartBox);
}
```

### Step 5: Keep the logic clean
To make the project more professional, you can later create a dedicated `Cart` class that manages the cart items. That is a better object-oriented design than keeping everything inside one controller.

---

## 6. Why this project is a good OOP example
This project is simple, but it already shows important object-oriented ideas:
- classes and objects,
- encapsulation,
- modeling real-world entities,
- separating UI and logic,
- using controllers to manage behavior.

That is exactly what second-year students should understand when learning OOP with Java.

---

## 7. Short presentation summary
If you want to explain this project in class, say this:

“This project shows how a JavaFX application can be built using object-oriented programming. The app uses classes to represent the window, the controller logic, the view, and the products. The UI is designed with FXML, while Java classes handle the behavior. This makes the code easier to organize, expand, and maintain.”
