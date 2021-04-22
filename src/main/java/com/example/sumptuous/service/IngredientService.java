package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> searchIngredients(String name){
        List<Ingredient> ingredients = ingredientRepository.searchByName(name);
        return ingredients;
    }

    public List<Ingredient> getIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients;
    }

}
