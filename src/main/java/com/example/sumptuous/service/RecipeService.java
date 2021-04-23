package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.Recipe;
import com.example.sumptuous.bean.RecipeIngredient;
import com.example.sumptuous.bean.RecipeIngredientKey;
import com.example.sumptuous.dao.RecipeIngredientRepository;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dto.DtoConversion;
import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    public List<RecipeDto> searchRecipes(List<Ingredient> ingredients){

        List<Recipe> recipes = recipeRepository.findByIngredientPattern(makeIngredientsPattern(ingredients));
        System.out.println("directions ----- "+recipes.get(0).getDirections());
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for(Recipe recipe : recipes){
            recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
        }
        return recipeDtos;
    }

    public RecipeDto addRecipe(RecipeRequestDto recipeRequestDto){
        Recipe recipe = DtoConversion.recipeRequestDtoToRecipe(recipeRequestDto);
        recipeRepository.save(recipe);
        Set<RecipeIngredient> recipeIngredients = new HashSet<RecipeIngredient>();
        List<Ingredient> ingredients = new ArrayList<>();
        for(IngredientDto ingredientDto : recipeRequestDto.getIngredientDtos()){
            Ingredient ingredient = DtoConversion.ingredientDtoToIngredient(ingredientDto);
            System.out.println("Ingredient id name : "+ingredient.getId()+"  name : "+ingredient.getName());
            ingredients.add(ingredient);
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredients.add(recipeIngredient);
            //RecipeIngredientKey recipeIngredientKey = new RecipeIngredientKey();
            //recipeIngredientKey.setRecipeId(recipe.getId());
            //recipeIngredientKey.setIngredientId(ingredient.getId());
            recipeIngredientRepository.save(recipeIngredient);
        }
        recipe.setRecipeIngredients(recipeIngredients);
        recipe.setIngredientPattern(makeIngredientsPattern(ingredients));
        recipe.setIngredientPattern(makeIngredientsPattern(ingredients));
        recipeRepository.save(recipe);
        return DtoConversion.convertRecipeToRecipeDto(recipe);
    }

    public String makeIngredientsPattern(List<Ingredient> ingredients){
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
        return sb.toString();
    }
}
