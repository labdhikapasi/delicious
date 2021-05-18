package com.example.sumptuous.controller;

import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import com.example.sumptuous.dto.RecipeRequestUserDto;
import com.example.sumptuous.dto.RecipeResponseUserDto;
import com.example.sumptuous.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/recipes")
    public RecipeResponseUserDto searchRecipes(@RequestBody RecipeRequestUserDto recipeRequestUserDto) {
        logger.info("[searchRecipes] argument : recipeRequestUserDto : "+recipeRequestUserDto.toString());
        System.out.println("recipeRequestUserDto : "+recipeRequestUserDto);
        RecipeResponseUserDto recipeResponseUserDto = recipeService.searchRecipes(recipeRequestUserDto);
        System.out.println("recipeResponseUserDto : "+recipeResponseUserDto);
        logger.info("[searchRecipes] response : recipeResponseUserDto : "+recipeResponseUserDto.toString());
        return recipeResponseUserDto;
    }
    @PostMapping("/addRecipe")
    public RecipeDto addRecipe(@RequestBody RecipeRequestDto recipeRequestDto) {
        logger.info("[addRecipe] argument : recipeRequestDto : "+recipeRequestDto.toString());
        System.out.println(recipeRequestDto.toString());
        RecipeDto recipeDto = recipeService.addRecipe(recipeRequestDto);
        logger.info("[addRecipe] response : recipeDto : "+recipeDto.toString());
        return recipeDto;
    }
    @GetMapping("/recipe")
    public Boolean findRecipeByName(@RequestParam String name){
        logger.info("[findRecipeByName] argument : name : "+name);
        System.out.println("recipe name : "+name);
        Boolean bool = recipeService.findRecipeByName(name);
        logger.info("[findRecipeByName] response : bool : "+bool);
        return bool;
    }

    @GetMapping("/approveRecipe")
    public Boolean approveRecipe(@RequestParam Long id){
        logger.info("[approveRecipe] argument : id : "+id);
        Boolean bool = recipeService.approveRecipe(id);
        logger.info("[approveRecipe] response : bool : "+bool);
        return bool;
    }
    @GetMapping("/unApprovedRecipes")
    public List<RecipeDto> unApprovedRecipes(){
        logger.info("[unApprovedRecipes]");
        List<RecipeDto> recipeDtos = recipeService.getUnApprovedRecipes();
        logger.info("[unApprovedRecipes] response : recipeDtos : "+recipeDtos.toString());
        return recipeDtos;
    }
    @GetMapping("/rejectRecipe")
    public Boolean rejectRecipe(@RequestParam Long id){
        logger.info("[rejectRecipe] argument : id : "+id);
        Boolean bool = recipeService.rejectRecipe(id);
        logger.info("[rejectRecipe] response : bool : "+bool);
        return bool;
    }
    @GetMapping("/getRecipesByDishType")
    public List<RecipeDto> getRecipesByDishType(@RequestParam String dishType){
        logger.info("[getRecipesByDishType] argument : dishType : "+dishType);
        List<RecipeDto> recipeDtos = recipeService.getRecipesByDishType(dishType);
        logger.info("[getRecipesByDishType] response : recipeDtos : "+recipeDtos.toString());
        return recipeDtos;
    }


}
