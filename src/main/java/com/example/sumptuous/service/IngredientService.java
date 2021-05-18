package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dao.IngredientRepository;
import com.example.sumptuous.dto.DtoConversion;
import com.example.sumptuous.dto.IngredientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    private static final Logger logger = LoggerFactory.getLogger(IngredientService.class);
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> searchIngredients(String name){
        logger.info("[searchIngredients] argument : name : "+name);
        List<Ingredient> ingredients = ingredientRepository.searchByName(name);
        logger.info("[searchIngredients] response : ingredients : "+ingredients.toString());
        return ingredients;
    }

    public List<IngredientDto> getIngredients(){
        logger.info("[getIngredients] ");
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for(Ingredient ingredient : ingredients){
            ingredientDtos.add(DtoConversion.ingredientToIngredientDto(ingredient));
        }
        logger.info("[getIngredients] response : ingredientDtos : "+ingredientDtos.toString());
        return ingredientDtos;
    }

    public IngredientDto addIngredient(IngredientDto ingredientDto){
        logger.info("[addIngredient] argument : ingredientDto : "+ingredientDto.toString());
        Ingredient ingredient =  ingredientRepository.save(DtoConversion.ingredientDtoToIngredient(ingredientDto));
        logger.info("[addIngredient] response : ingredientDto : "+ingredientDto.toString());
        return DtoConversion.ingredientToIngredientDto(ingredient);
    }

    public Boolean checkIngredientByName(String name){
        logger.info("[checkIngredientByName] argument : name : "+name);
        Ingredient ingredient = ingredientRepository.findByName(name);
        if(null != ingredient){
            logger.info("[checkIngredientByName] response : bool : "+true);
            return true;
        }
        else{
            logger.info("[checkIngredientByName] response : bool : "+false);
            return false;
        }
    }

}
