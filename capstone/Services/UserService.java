package com.example.capstone.Services;

import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Model.Product;
import com.example.capstone.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@AllArgsConstructor
public class UserService {


    ArrayList<User> userList = new ArrayList<>();
    public final MerchantStockService merchantStockService;
    public final ProductService productService;
    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public void addUser(User newUser) {

        for (User user : userList) {
            if(user.getId().equals(newUser.getId())){
                return;
            }

        }
        userList.add(user);

    }

    public boolean updateUser(String id, User updatedUser){
        for (User user : userList) {
            if(user.getId().equals(id)){
                userList.set(userList.indexOf(user), updatedUser);
                return true;
            }

        }
        return false;


    }

    public boolean deleteUser(String id){
        for (User user : userList) {
            if(user.getId().equals(id)){
                userList.remove(userList.indexOf(user));
                return true;
            }

        }
        return false;
    }


    public boolean addToStock(String userID ,String productID, String merchantID, int amount){


        System.out.println("inside the method");
        for (User user : userList){
            if (user.getId().equals(userID)){
                System.out.println("pass user id");
                if (user.getRole().equalsIgnoreCase("Admin")) {

                    for (MerchantStock merchantStock : merchantStockService.merchantStocks) {
                        if (merchantStock.getProductID().equals(productID) && merchantStock.getMerchantID().equals(merchantID)) {
                            merchantStock.setStock(merchantStock.getStock() + amount);
                            return true;
                        }
                    }
                }
        }

        }
        return false;
    }


    public boolean purchase(String userID, String productID, String merchantID){
        System.out.println("inside the purchase");
        for (User user : userList){
            if (user.getId().equals(userID)){
                System.out.println("pass the user id");
                for (MerchantStock merchantStock : merchantStockService.merchantStocks){
                    if (merchantStock.getProductID().equals(productID) && merchantStock.getMerchantID().equals(merchantID)){
                        System.out.println("pass product id and stock id");
                       if (merchantStock.getStock() > 0){
                           System.out.println("pass the stock > ");
                           for (Product product : productService.productList) {
                                if (product.getId().equals(productID)) {
                                    double discount = user.getIsPrime() ? product.getPrice() * 0.1 : 0.0;
                                    double totalPrice = product.getPrice() - discount;
                                    if (user.getBalance() >= totalPrice) {
                                        System.out.println("pass the user balance");
                                        merchantStock.setStock(merchantStock.getStock() - 1);
                                        user.setBalance(user.getBalance() - totalPrice);
                                        user.addProductToOrderHistory(product);
                                        return true;

                                    }
                                }
                           }
                       }
                    }
                }
            }

        }
        return false;
    }

public boolean upgradeToPrime(String id){
        for (User user : userList){
            if (user.getId().equals(id)){
                if (user.getIsPrime()){
                    return false;
                }

                if (user.getBalance() >= 10){
                    user.setBalance(user.getBalance() - 10);
                    user.setIsPrime(true);
                    return true;
                }


            }
        }
        return false;
}

    public ArrayList<Product> getAllOrderHistory(String userId) {
        for (User user : userList){
            if (user.getId().equals(userId)){
                return user.getOrderHistory();
            }
        }

        return null;
    }


}
