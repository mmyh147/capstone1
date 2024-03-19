package com.example.capstone.Controller;

import com.example.capstone.Model.Product;
import com.example.capstone.Model.User;
import com.example.capstone.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping("get")
    public ResponseEntity getAllUsers() {

        ArrayList<User> UserList = userService.getAllUsers();
        return ResponseEntity.status(200).body(UserList);
    }

    @PostMapping("post")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(400).body("User successfully updated");

        } else {
            return ResponseEntity.status(200).body("User not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {

        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("User deleted");

        } else {
            return ResponseEntity.status(400).body("User not found");

        }
    }


    @PutMapping("/stock/add/{userId}/{productId}/{merchantId}/{amount}")
    public ResponseEntity addToStock(@PathVariable String userId, @PathVariable String productId,
                              @PathVariable String merchantId, @PathVariable int amount) {
         boolean isAdded = userService.addToStock(userId, productId, merchantId, amount);
        if (isAdded) {
            return ResponseEntity.status(200).body("stock has been added");

        } else {
            return ResponseEntity.status(400).body("stock not found");

        }
    }


    @PutMapping("/purchase/{userId}/{productId}/{merchantId}")
    public ResponseEntity addToStock(@PathVariable String userId, @PathVariable String productId,
                                     @PathVariable String merchantId) {
        boolean isPurchased = userService.purchase(userId, productId, merchantId);
        if (isPurchased) {
            return ResponseEntity.status(200).body("the item purchased");

        } else {
            return ResponseEntity.status(400).body("the item not found");

        }
    }


    @PutMapping("prime/{userId}")
    public ResponseEntity upgradeToPrime(@PathVariable String userId) {

        boolean isUpgraded = userService.upgradeToPrime(userId);
        if (isUpgraded) {
            return ResponseEntity.status(200).body("User upgraded to prime");

        } else {
            return ResponseEntity.status(400).body("upgrade fail");

        }
    }

    @GetMapping("history/{userId}")
    public ResponseEntity history(@PathVariable String userId) {
        ArrayList<Product> historyList = userService.getAllOrderHistory(userId);
        if (historyList.isEmpty()){
            return ResponseEntity.status(400).body("No order found");

        }
        return ResponseEntity.status(200).body(historyList);

    }
}
