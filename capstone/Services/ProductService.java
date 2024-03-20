package com.example.capstone.Services;

import com.example.capstone.Model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductService {

    ArrayList<Product> productList = new ArrayList<>();

    public ArrayList<Product> getAllProducts() {
        return productList;
    }

    public void addProduct(Product newProduct) {

        for (Product product : productList) {
            if(product.getId().equals(newProduct.getId())){
                return;
            }

        }
        productList.add(product);

    }

    public boolean updateProduct(String id, Product updatedProduct){
        for (Product product : productList) {
            if(product.getId().equals(id)){
                productList.set(productList.indexOf(product), updatedProduct);
                return true;
            }

        }
        return false;


    }

    public boolean deleteProduct(String id){
        for (Product product : productList) {
            if(product.getId().equals(id)){
                productList.remove(productList.indexOf(product));
                return true;
            }

        }
        return false;
    }

}
