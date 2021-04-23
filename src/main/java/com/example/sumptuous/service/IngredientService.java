package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.dao.IngredientRepository;
import com.example.sumptuous.dto.DtoConversion;
import com.example.sumptuous.dto.IngredientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> searchIngredients(String name){
        List<Ingredient> ingredients = ingredientRepository.searchByName(name);
        return ingredients;
    }

    public List<IngredientDto> getIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for(Ingredient ingredient : ingredients){
            ingredientDtos.add(DtoConversion.ingredientToIngredientDto(ingredient));
        }
        return ingredientDtos;
    }

    public IngredientDto addIngredient(IngredientDto ingredientDto){

        Ingredient ingredient =  ingredientRepository.save(DtoConversion.ingredientDtoToIngredient(ingredientDto));
        return DtoConversion.ingredientToIngredientDto(ingredient);
    }

}
