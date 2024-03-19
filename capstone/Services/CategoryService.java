package com.example.capstone.Services;

import com.example.capstone.Model.Category;
import com.example.capstone.Model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAllCategories() {
        return categories;
    }

    public void addCategory(Category category) {

        categories.add(category);

    }

    public boolean updateCategory(String id, Category updatedCategory){
        for (Category category : categories) {
            if(category.getId().equals(id)){
                categories.set(categories.indexOf(category), updatedCategory);
                return true;
            }

        }
        return false;


    }

    public boolean deleteCategory(String id){
        for (Category category : categories) {
            if(category.getId().equals(id)){
                categories.remove(categories.indexOf(category));
                return true;
            }

        }
        return false;
    }



}
