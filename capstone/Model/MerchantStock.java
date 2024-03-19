package com.example.capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "Product ID must not be empty")
    private String productID;
    @NotEmpty(message = "Merchant ID must not be empty")
    private String merchantID;
    @NotNull
    private Integer stock;

}
