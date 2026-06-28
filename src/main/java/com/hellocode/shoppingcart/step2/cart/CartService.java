// Simple shared cart state used by the home page and cart page.
package com.hellocode.shoppingcart.step2.cart;

import com.hellocode.shoppingcart.step2.domain.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CartService {

    private static final CartService INSTANCE = new CartService();

    private final Map<Product, Integer> items = new LinkedHashMap<>();

    private CartService() {
    }

    public static CartService getInstance() {
        return INSTANCE;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void add(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void increment(Product product) {
        add(product);
    }

    public void decrement(Product product) {
        Integer quantity = items.get(product);
        if (quantity == null) {
            return;
        }
        if (quantity <= 1) {
            items.remove(product);
        } else {
            items.put(product, quantity - 1);
        }
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        int count = 0;
        for (Integer quantity : items.values()) {
            count += quantity;
        }
        return count;
    }

    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }

    public BigDecimal getTotal() {
        // Add up each product price multiplied by its quantity.
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total = total.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total;
    }
}
