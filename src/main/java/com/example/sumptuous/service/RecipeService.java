package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.Recipe;
import com.example.sumptuous.bean.RecipeIngredient;
import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.RecipeIngredientRepository;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dao.UserRepository;
import com.example.sumptuous.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private UserRepository userRepository;

    public RecipeResponseUserDto searchRecipes(RecipeRequestUserDto recipeRequestUserDto){
        RecipeResponseUserDto recipeResponseUserDto = new RecipeResponseUserDto();
        String dishType = recipeRequestUserDto.getDishType();
        String mealType = recipeRequestUserDto.getMealType();
        String name = recipeRequestUserDto.getName();
        Integer cookingTime = recipeRequestUserDto.getCookingTime();
        if(null==recipeRequestUserDto.getIngredientDtos()){
            List<RecipeDto> recipeDtos = new ArrayList<>();
            if(null==recipeRequestUserDto.getCookingTime()){
                List<Recipe> recipes = recipeRepository.searchByMealTypeAndDishTypeAndName(mealType,dishType,name);
                for(Recipe recipe : recipes){
                    recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
                }
            }
            else{
                List<Recipe> recipes = recipeRepository.searchByMealTypeAndDishTypeAndNameAndCookingTime(mealType,dishType,name,cookingTime);
                for(Recipe recipe : recipes){
                    recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
                }
            }
            recipeResponseUserDto.setRecipeDtos(recipeDtos);
        }
        else{

            List<Ingredient> ingredients = new ArrayList<>();

            for(IngredientDto ingredientDto : recipeRequestUserDto.getIngredientDtos()){
                ingredients.add(DtoConversion.ingredientDtoToIngredient(ingredientDto));
            }
            String ingredientPattern = makeIngredientsPattern(ingredients);
            List<Recipe> recipes = recipeRepository.searchByIngredientPatternAndMealTypeAndDishType(ingredientPattern,mealType,dishType);
            List<RecipeDto> recipeDtos = new ArrayList<>();
            for(Recipe recipe : recipes){
                recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
            }
            recipeResponseUserDto.setRecipeDtos(recipeDtos);
            if(recipeRequestUserDto.getChecked()){

                List<RecipeDto> otherRecipeDtos = new ArrayList<>();
                List<Recipe> otherRecipes = recipeRepository.searchByNotIngredientPatternAndMealTypeAndDishType(ingredientPattern,mealType,dishType);

                for(Recipe recipe : otherRecipes){

                    List<Ingredient> currentIngredients = new ArrayList<>();
                    for(RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()){
                        currentIngredients.add(recipeIngredient.getIngredient());
                    }
                    if(currentIngredients.containsAll(ingredients)){

                        RecipeDto currentRecipeDto = DtoConversion.convertRecipeToRecipeDto(recipe);
                        List<IngredientDto> remainingIngredientDtos = new ArrayList<>();
                        currentIngredients.removeAll(ingredients);
                        for(Ingredient ingredient : currentIngredients){
                            remainingIngredientDtos.add(DtoConversion.ingredientToIngredientDto(ingredient));
                        }
                        currentRecipeDto.setIngredientDtos(remainingIngredientDtos);
                        otherRecipeDtos.add(currentRecipeDto);
                    }
                }

                recipeResponseUserDto.setOtherRecipeDtos(otherRecipeDtos);

            }

        }

        //System.out.println("directions ----- "+recipes.get(0).getDirections());


        return recipeResponseUserDto;
    }

    public RecipeDto addRecipe(RecipeRequestDto recipeRequestDto){
        Optional<User> optionalUser = userRepository.findById(recipeRequestDto.getUserId());
        User user = optionalUser.get();
        Recipe recipe = DtoConversion.recipeRequestDtoToRecipe(recipeRequestDto);
        if(user.getId()==1){
            recipe.setApproved(1);
        }else{
            recipe.setApproved(0);
        }
        recipe.setDeleted(0);
        recipe.setCreatedBy(user);
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
        System.out.println("ingredient pattern "+sb.toString());
        return sb.toString();
    }

    public Boolean findRecipeByName(String name){
        Recipe recipe = recipeRepository.findByName(name);
        if(recipe != null){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean approveRecipe(Long id){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Recipe recipe = optionalRecipe.get();
        if(recipe != null){
            recipe.setApproved(1);
            recipeRepository.save(recipe);
            return true;
        }else{
            return false;
        }
    }

    public Boolean rejectRecipe(Long id){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Recipe recipe = optionalRecipe.get();
        if(recipe != null){
            recipe.setApproved(0);
            recipe.setDeleted(1);
            recipeRepository.save(recipe);
            return true;
        }else{
            return false;
        }
    }

    public List<RecipeDto> getUnApprovedRecipes(){
        List<RecipeDto> recipeDtos = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findByApprovedAndDeleted(0,0);
        for(Recipe recipe : recipes){
            recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
        }
        return recipeDtos;
    }
}
