package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dao.IngredientRepository;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dto.DtoConversion;
import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RecipeRepository recipeRepository;

    /*public RecipeDto addRecipe(RecipeRequestDto recipeRequestDto){
        ingredientRepository.save(ingredient);
        return  DtoConversion.ingredientToIngredientDto(ingredient);
    }*/
}
