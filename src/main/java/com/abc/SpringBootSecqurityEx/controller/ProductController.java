package com.abc.SpringBootSecqurityEx.controller;

import com.abc.SpringBootSecqurityEx.dtos.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    // ✅ Mutable list (important!)
    private final List<Product> products = new ArrayList<>(List.of(
            new Product(
                    1L,
                    "Laptop",
                    "Electronics",
                    999.99,
                    "High-performance laptop",
                    10,
                    true,
                    4.5,
                    "https://picsum.photos/200",
                    LocalDateTime.now()
            ),
            new Product(
                    2L,
                    "Book",
                    "Education",
                    29.99,
                    "Java Programming Book",
                    50,
                    true,
                    4.2,
                    "https://picsum.photos/201",
                    LocalDateTime.now()
            )
    ));

    // 🔓 PUBLIC: Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    // 🔐 AUTHENTICATED USERS ONLY
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🛡 MODERATOR / ADMIN
    @PostMapping
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = new Product(
                (long) (products.size() + 1),
                product.name(),
                product.category(),
                product.price(),
                product.description(),
                product.stock(),
                product.active(),
                product.rating(),
                product.imageUrl(),
                LocalDateTime.now()
        );

        products.add(newProduct);
        return ResponseEntity.ok(newProduct);
    }

    // 🛡 MODERATOR / ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id().equals(id)) {
                Product updatedProduct = new Product(
                        id,
                        product.name(),
                        product.category(),
                        product.price(),
                        product.description(),
                        product.stock(),
                        product.active(),
                        product.rating(),
                        product.imageUrl(),
                        products.get(i).createdAt()
                );
                products.set(i, updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // 👑 ADMIN ONLY
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.id().equals(id));
        return removed
                ? ResponseEntity.ok("Product deleted successfully")
                : ResponseEntity.notFound().build();
    }

    // 💎 PREMIUM USERS ONLY
    @GetMapping("/premium")
    @PreAuthorize("hasRole('PREMIUM_USER')")
    public ResponseEntity<List<Product>> getPremiumProducts() {
        List<Product> premiumProducts = List.of(
                new Product(
                        100L,
                        "Premium Laptop",
                        "Electronics",
                        1999.99,
                        "Ultra high-end laptop",
                        5,
                        true,
                        4.9,
                        "https://picsum.photos/300",
                        LocalDateTime.now()
                )
        );
        return ResponseEntity.ok(premiumProducts);
    }
}
