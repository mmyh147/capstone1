package com.example.capstone.Controller;

import com.example.capstone.Model.Merchant;
import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Services.MerchantService;
import com.example.capstone.Services.MerchantStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
public class MerchantStockController {


    private final MerchantStockService merchantService;


    @GetMapping("get")
    public ResponseEntity getAllMerchants() {

        ArrayList<MerchantStock> UserList = merchantService.getAllMerchantsStocks();
        return ResponseEntity.status(200).body(UserList);
    }

    @PostMapping("post")
    public ResponseEntity addMerchant(@RequestBody @Valid MerchantStock merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchantStock(merchant);
        return ResponseEntity.status(200).body("stock added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody MerchantStock merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = merchantService.updateMerchantStock(id, merchant);
        if (isUpdated) {
            return ResponseEntity.status(400).body("stock successfully updated");

        } else {
            return ResponseEntity.status(200).body("stock not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {

        boolean isDeleted = merchantService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("stock deleted");

        } else {
            return ResponseEntity.status(400).body("stock not found");

        }
    }


}
