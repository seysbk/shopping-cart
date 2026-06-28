// Product catalog for the clothing store, including image paths and prices.
package com.hellocode.shoppingcart.step2.domain;

import java.math.BigDecimal;

public enum Product {
    TSHIRT("/com/hellocode/shoppingcart/step2/images/tshirt.png", BigDecimal.valueOf(19.99)),
    JEANS("/com/hellocode/shoppingcart/step2/images/jeans.png", BigDecimal.valueOf(34.99)),
    HOODIE("/com/hellocode/shoppingcart/step2/images/hoodie.png", BigDecimal.valueOf(42.50)),
    SNEAKERS("/com/hellocode/shoppingcart/step2/images/sneakers.png", BigDecimal.valueOf(49.99)),
    JACKET("/com/hellocode/shoppingcart/step2/images/jacket.png", BigDecimal.valueOf(59.99)),
    CAP("/com/hellocode/shoppingcart/step2/images/cap.png", BigDecimal.valueOf(14.99)),
    DRESS("/com/hellocode/shoppingcart/step2/images/dress.png", BigDecimal.valueOf(64.99)),
    SKIRT("/com/hellocode/shoppingcart/step2/images/skirt.png", BigDecimal.valueOf(29.99)),
    BELT("/com/hellocode/shoppingcart/step2/images/belt.png", BigDecimal.valueOf(18.50)),
    WATCH("/com/hellocode/shoppingcart/step2/images/watch.png", BigDecimal.valueOf(89.99));

    private final String imagePath;
    private final BigDecimal price;

    Product(String imagePath, BigDecimal price){
        this.imagePath = imagePath;
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
