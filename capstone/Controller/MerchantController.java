package com.example.capstone.Controller;


import com.example.capstone.Model.Merchant;
import com.example.capstone.Model.Product;
import com.example.capstone.Services.MerchantService;
import com.example.capstone.Services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@AllArgsConstructor
public class MerchantController {


    private final MerchantService merchantService;


    @GetMapping("get")
    public ResponseEntity getAllMerchants() {

        ArrayList<Merchant> UserList = merchantService.getAllMerchants();
        return ResponseEntity.status(200).body(UserList);
    }

    @PostMapping("post")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("merchant added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Merchant successfully updated");

        } else {
            return ResponseEntity.status(400).body("Merchant not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {

        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("merchant deleted");

        } else {
            return ResponseEntity.status(400).body("merchant not found");

        }
    }


}
