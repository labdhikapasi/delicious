package com.example.sumptuous.controller;

import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.service.IngredientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class IngredientController {
    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);
    @Autowired
    private IngredientService ingredientService;

    /*@PostMapping("/ingredients")
    public List<Ingredient> searchIngredients(@RequestBody Ingredient ingredient){
        return ingredientService.searchIngredients(ingredient.getName());
    }*/
    @GetMapping("/ingredient")
    public Boolean checkIngredientByName(@RequestParam String name){
        logger.info("[checkIngredientByName] argument : name : "+name);
        Boolean bool = ingredientService.checkIngredientByName(name);
        logger.info("[checkIngredientByName] response : bool : "+bool);
        return bool;
    }
    @GetMapping("/ingredients")
    public List<IngredientDto> getIngredients(){
        logger.info("[getIngredients]");
        List<IngredientDto> ingredientDtos = ingredientService.getIngredients();
        logger.info("[getIngredients] response : ingredientDtos : "+ingredientDtos.toString());
        return ingredientDtos;
    }

    @PostMapping("/ingredient")
    public IngredientDto addIngredient(@RequestBody IngredientDto ingredientDto){
        logger.info("[addIngredient] argument : ingredientDto : "+ingredientDto.toString());
        IngredientDto ingredientDto1 = ingredientService.addIngredient(ingredientDto);
        logger.info("[addIngredient] response : ingredientDto1 : "+ingredientDto1.toString());
        return ingredientDto1;
    }

}
