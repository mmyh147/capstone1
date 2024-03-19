package com.example.capstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "ID must not be empty")
    @Size(min = 4, message = "name must be more than 3 characters")
    private String name;

    @NotNull(message = "price must be not empty")
    @Positive(message = "price must be positive")
    private Double price;

    @NotEmpty(message = "Must not be empty")
    private String categoryID;

}
