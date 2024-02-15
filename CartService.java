package service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController

public class CartService {
    private Map<String, Integer> cart;

    public CartService() {
        cart = new HashMap<>();
    }
    @PostMapping("/api/cart/add")
    public void addToCart(@RequestParam String item, @RequestParam int quantity) {
        cart.put(item, quantity);
    }

    @GetMapping("/api/cart/total")
    public double calculateTotal() {
        double total = 0.0;
        for (String item : cart.keySet()) {
            int quantity = cart.get(item);
            // Fetch the price of the item from the Product Service
            double price = getPriceFromProductService(item);
            total += price * quantity;
        }
        return total;
    }

    private double getPriceFromProductService(String item) {
        // Assume calling Product Service API to fetch price
        // In real-world scenario, this would involve making an HTTP request to Product Service
        // For simplicity, just return a hardcoded price here
        switch (item) {
            case "apple":
                return 1.50;
            case "banana":
                return 0.75;
            case "orange":
                return 1.25;
            default:
                return 0.0;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CartService.class, args);
    }
}

