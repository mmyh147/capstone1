package com.example.capstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "username must not be empty")
    @Size(min = 6, message = "must have over 5 characters")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}$", message = "password must have at least 6 characters and contain both letters and digits")
    private String password;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "please enter valid email")
    private String email;
    @Pattern(regexp = "^(Admin|Customer)$")
    private String role;
    @NotNull
    @Positive
    private Double balance;
    @NotNull
    @AssertFalse
    private Boolean isPrime;

    private ArrayList<Product> orderHistory;
    private ArrayList<Movie> watchList;


    public User() {
        this.orderHistory = new ArrayList<>();
        this.watchList = new ArrayList<>();
    }

    public void addProductToOrderHistory(Product product) {
        if (orderHistory == null) {
            orderHistory = new ArrayList<>();
        }
        orderHistory.add(product);
    }

    public void addMovieToWatchList(Movie movie) {
        if (watchList == null) {
            watchList = new ArrayList<>();
        }
        watchList.add(movie);
    }

}
