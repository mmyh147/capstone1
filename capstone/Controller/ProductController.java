package com.example.capstone.Controller;

import com.example.capstone.Model.Product;
import com.example.capstone.Services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor

public class ProductController {


    private final ProductService productService;


    @GetMapping("get")
    public ResponseEntity getAllProducts() {

        ArrayList<Product> productsList = productService.getAllProducts();
        return ResponseEntity.status(200).body(productsList);
    }

    @PostMapping("post")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(400).body("product successfully updated");

        } else {
            return ResponseEntity.status(200).body("product not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {

        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Product deleted");

        } else {
            return ResponseEntity.status(400).body("Product not found");

        }
    }

}
