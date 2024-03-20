package com.example.capstone.Services;

import com.example.capstone.Model.Merchant;
import com.example.capstone.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {


    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getAllMerchantsStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock newMerchant) {

        for (MerchantStock merchant : merchantStocks) {
            if(merchant.getId().equals(newMerchant.getId())){
                return;
            }
        merchantStocks.add(merchant);

    }

    public boolean updateMerchantStock(String id, MerchantStock updatedMerchant){
        for (MerchantStock merchant : merchantStocks) {
            if(merchant.getId().equals(id)){
                merchantStocks.set(merchantStocks.indexOf(merchant), updatedMerchant);
                return true;
            }

        }
        return false;


    }

    public boolean deleteMerchantStock(String id){
        for (MerchantStock merchant : merchantStocks) {
            if(merchant.getId().equals(id)){
                merchantStocks.remove(merchantStocks.indexOf(merchant));
                return true;
            }

        }
        return false;
    }



}
