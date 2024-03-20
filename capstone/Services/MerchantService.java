package com.example.capstone.Services;

import com.example.capstone.Model.Category;
import com.example.capstone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantService {



    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAllMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant newMerchant) {

        for (Merchant merchant : merchants) {
            if(merchant.getId().equals(newMerchant.getId)){
                return;
            }
        merchants.add(merchant);

    }

    public boolean updateMerchant(String id, Merchant updatedMerchant){
        for (Merchant merchant : merchants) {
            if(merchant.getId().equals(id)){
                merchants.set(merchants.indexOf(merchant), updatedMerchant);
                return true;
            }

        }
        return false;


    }

    public boolean deleteMerchant(String id){
        for (Merchant merchant : merchants) {
            if(merchant.getId().equals(id)){
                merchants.remove(merchants.indexOf(merchant));
                return true;
            }

        }
        return false;
    }




}
