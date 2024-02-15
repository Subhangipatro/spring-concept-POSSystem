package service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController

public class ProductService {
    private Map<String, Double> products;

    public ProductService() {
        products = new HashMap<>();
        products.put("apple", 1.50);
        products.put("banana", 0.75);
        products.put("orange", 1.25);
    }

    @GetMapping("/api/products")
    public Map<String, Double> getProducts() {
        return products;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductService.class, args);
    }
}


