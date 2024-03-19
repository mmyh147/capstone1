package com.example.capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String Category;
}
