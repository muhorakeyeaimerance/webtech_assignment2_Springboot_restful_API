package com.example.product_api_26970.controller.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_api_26970.model.ecommerce.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> productList = new ArrayList<>();

    public ProductController() {

        productList.add(new Product(1L, "Laptop", "Gaming laptop", 1200.0, "Electronics", 5, "Dell"));
        productList.add(new Product(2L, "Smartphone", "Android phone", 800.0, "Electronics", 10, "Samsung"));
        productList.add(new Product(3L, "Headphones", "Wireless headphones", 150.0, "Accessories", 20, "Sony"));
        productList.add(new Product(4L, "Backpack", "Travel backpack", 60.0, "Bags", 15, "Nike"));
        productList.add(new Product(5L, "Sneakers", "Running shoes", 120.0, "Fashion", 8, "Adidas"));
        productList.add(new Product(6L, "Tablet", "10-inch tablet", 400.0, "Electronics", 7, "Apple"));
        productList.add(new Product(7L, "Watch", "Smart watch", 250.0, "Accessories", 12, "Huawei"));
        productList.add(new Product(8L, "Keyboard", "Mechanical keyboard", 90.0, "Electronics", 0, "Logitech"));
        productList.add(new Product(9L, "Jacket", "Winter jacket", 200.0, "Fashion", 4, "Puma"));
        productList.add(new Product(10L, "Mouse", "Wireless mouse", 45.0, "Electronics", 25, "HP"));
    }

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit) {

        int start = page * limit;
        int end = Math.min(start + limit, productList.size());

        if (start > productList.size()) {
            return new ArrayList<>();
        }

        return productList.subList(start, end);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/search")
    public List<Product> searchByKeyword(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/price-range")
    public List<Product> getByPriceRange(@RequestParam Double min,
                                         @RequestParam Double max) {

        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/in-stock")
    public List<Product> getInStock() {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getStockQuantity() > 0) {
                result.add(p);
            }
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        productList.add(product);
        return product;
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId,
                                 @RequestBody Product updatedProduct) {

        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                p.setName(updatedProduct.getName());
                p.setDescription(updatedProduct.getDescription());
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setStockQuantity(updatedProduct.getStockQuantity());
                p.setBrand(updatedProduct.getBrand());
                return p;
            }
        }

        throw new RuntimeException("Product not found");
    }

    @PatchMapping("/{productId}/stock")
    public Product updateStock(@PathVariable Long productId,
                               @RequestParam int quantity) {

        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return p;
            }
        }

        throw new RuntimeException("Product not found");
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productList.removeIf(p -> p.getProductId().equals(productId));
    }
}
