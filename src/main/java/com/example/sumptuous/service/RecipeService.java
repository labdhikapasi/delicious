package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.Recipe;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dto.DtoConversion;
import com.example.sumptuous.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDto> searchRecipes(List<Ingredient> ingredients){
        List<Long> ids = new ArrayList<>();
        for(Ingredient ingredient : ingredients){
            ids.add(ingredient.getId());
        }
        Collections.sort(ids);
        StringBuffer sb = new StringBuffer();

        for (Long id : ids) {
            sb.append(id);
            sb.append("|");
        }
        List<Recipe> recipes = recipeRepository.findByIngredientPattern(sb.toString());
        System.out.println("directions ----- "+recipes.get(0).getDirections().toString());
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for(Recipe recipe : recipes){
            recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
        }
        return recipeDtos;
    }
}
