package com.example.sumptuous.controller;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    /*@PostMapping("/ingredients")
    public List<Ingredient> searchIngredients(@RequestBody Ingredient ingredient){
        return ingredientService.searchIngredients(ingredient.getName());
    }*/
    @GetMapping("/ingredient")
    public Boolean checkIngredientByName(@RequestParam String name){
        return ingredientService.checkIngredientByName(name);
    }
    @GetMapping("/ingredients")
    public List<IngredientDto> getIngredients(){
        return ingredientService.getIngredients();
    }

    @PostMapping("/ingredient")
    public IngredientDto addIngredient(@RequestBody IngredientDto ingredientDto){
        return ingredientService.addIngredient(ingredientDto);
    }

}
