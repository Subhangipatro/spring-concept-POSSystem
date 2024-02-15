package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RestController
@EnableAsync
public class POSSystem {

    private Map<String, Double> products;
    private Map<String, Integer> cart;

    public POSSystem() {
        products = new HashMap<>();
        products.put("apple", 1.50);
        products.put("banana", 0.75);
        products.put("orange", 1.25);
        cart = new HashMap<>();
    }

    @GetMapping("/products")
    public Map<String, Double> getProducts() {
        return products;
    }

    @PostMapping("/cart/add")
    public CompletableFuture<Void> addToCart(@RequestParam String item, @RequestParam int quantity) {
        return CompletableFuture.runAsync(() -> {
            if (products.containsKey(item)) {
                cart.put(item, quantity);
            } else {
                throw new IllegalArgumentException("Invalid product");
            }
        });
    }

    @GetMapping("/cart/total")
    public double calculateTotal() {
        double total = 0.0;
        for (String item : cart.keySet()) {
            int quantity = cart.get(item);
            total += products.get(item) * quantity;
        }
        return total;
    }

    public static void main(String[] args) {
        SpringApplication.run(POSSystem.class, args);
    }
}
