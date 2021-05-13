package com.example.sumptuous.controller;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import com.example.sumptuous.dto.RecipeRequestUserDto;
import com.example.sumptuous.dto.RecipeResponseUserDto;
import com.example.sumptuous.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/recipes")
    public RecipeResponseUserDto searchRecipes(@RequestBody RecipeRequestUserDto recipeRequestUserDto) {
        System.out.println("recipeRequestUserDto : "+recipeRequestUserDto);
        RecipeResponseUserDto recipeResponseUserDto = recipeService.searchRecipes(recipeRequestUserDto);
        System.out.println("recipeResponseUserDto : "+recipeResponseUserDto);
        return recipeResponseUserDto;
    }
    @PostMapping("/addRecipe")
    public RecipeDto addRecipe(@RequestBody RecipeRequestDto recipeRequestDto) {
        System.out.println(recipeRequestDto.toString());
        return recipeService.addRecipe(recipeRequestDto);
    }
    @GetMapping("/recipe")
    public Boolean findRecipeByName(@RequestParam String name){
        System.out.println("recipe name : "+name);
        return recipeService.findRecipeByName(name);
    }
}
