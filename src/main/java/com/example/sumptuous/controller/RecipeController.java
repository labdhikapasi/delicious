package com.example.sumptuous.controller;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import com.example.sumptuous.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/recipes")
    public List<RecipeDto> searchRecipes(@RequestBody List<Ingredient> ingredients) {
        return recipeService.searchRecipes(ingredients);
    }
    @PostMapping("/addRecipe")
    public RecipeDto addRecipe(@RequestBody RecipeRequestDto recipeRequestDto) {
        return recipeService.addRecipe(recipeRequestDto);
    }
}
