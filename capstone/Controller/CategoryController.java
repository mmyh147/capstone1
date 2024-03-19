package com.example.capstone.Controller;

import com.example.capstone.Model.Category;
import com.example.capstone.Model.Product;
import com.example.capstone.Services.CategoryService;
import com.example.capstone.Services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;


    @GetMapping("get")
    public ResponseEntity getAllCategories() {

        ArrayList<Category> UserList = categoryService.getAllCategories();
        return ResponseEntity.status(200).body(UserList);
    }

    @PostMapping("post")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated) {
            return ResponseEntity.status(400).body("category successfully updated");

        } else {
            return ResponseEntity.status(200).body("Category not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) {

        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("category deleted");

        } else {
            return ResponseEntity.status(400).body("category not found");

        }
    }
}
