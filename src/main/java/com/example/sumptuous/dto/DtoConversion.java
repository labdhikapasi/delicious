package com.example.sumptuous.dto;

import com.example.sumptuous.bean.Recipe;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DtoConversion {
    public static RecipeDto convertRecipeToRecipeDto(Recipe recipe){
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setName(recipe.getName());
        recipeDto.setAvgRating(recipe.getAvgRating());
        recipeDto.setCookingTime(recipe.getCookingTime());
        recipeDto.setDishType(recipe.getDishType()==null ? "": recipe.getDishType().toString());
        recipeDto.setMealType(recipe.getMealType()==null ? "": recipe.getMealType().toString());
        recipeDto.setServes(recipe.getServes());
        recipeDto.setImageUrl(recipe.getImageUrl());
        recipeDto.setVideoLink(recipe.getVideoLink());
        JSONArray jsonArray = new JSONArray(recipe.getDirections());
        List<String> listData = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++) {

            //Adding each element of JSON array into ArrayList
            listData.add(jsonArray.get(i).toString());
        }
        recipeDto.setDirections(listData);
        JSONArray jsonArrayList = new JSONArray(recipe.getIngredientsList());
        List<String> ingredientList = new ArrayList<>();
        for (int i=0;i<jsonArrayList.length();i++) {

            //Adding each element of JSON array into ArrayList
            ingredientList.add(jsonArrayList.get(i).toString());
        }
        recipeDto.setIngredientList(ingredientList);
        return recipeDto;
    }
}
