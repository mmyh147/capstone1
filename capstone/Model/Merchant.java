package com.example.capstone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "name must be not empty")
    @Size(min = 4, message = "name must be over 3 characters")
    private String name;


}
