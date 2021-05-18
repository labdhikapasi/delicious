package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.Recipe;
import com.example.sumptuous.bean.RecipeIngredient;
import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.RecipeIngredientRepository;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dao.UserRepository;
import com.example.sumptuous.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private UserRepository userRepository;

    public RecipeResponseUserDto searchRecipes(RecipeRequestUserDto recipeRequestUserDto){
        logger.info("[searchRecipes] argument : recipeRequestUserDto : "+recipeRequestUserDto.toString());
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

        logger.info("[searchRecipes] response : recipeResponseUserDto : "+recipeResponseUserDto.toString());
        return recipeResponseUserDto;
    }

    public RecipeDto addRecipe(RecipeRequestDto recipeRequestDto){
        logger.info("[addRecipe] argument : recipeRequestDto : "+recipeRequestDto.toString());
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
        RecipeDto recipeDto = DtoConversion.convertRecipeToRecipeDto(recipe);
        logger.info("[addRecipe] response : recipeDto : "+recipeDto.toString());
        return recipeDto;
    }

    public String makeIngredientsPattern(List<Ingredient> ingredients){
        logger.info("[makeIngredientsPattern] argument : ingredients : "+ingredients.toString());
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
        logger.info("[searchIngredients] response : ingredients : "+ingredients.toString());
        return sb.toString();
    }

    public Boolean findRecipeByName(String name){
        Recipe recipe = recipeRepository.findByName(name);
        if(recipe != null){
            logger.info("[makeIngredientsPattern] response : bool : "+true);
            return true;
        }
        else{
            logger.info("[makeIngredientsPattern] response : bool : "+false);
            return false;
        }
    }

    public Boolean approveRecipe(Long id){
        logger.info("[approveRecipe] argument : id : "+id);
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Recipe recipe = optionalRecipe.get();
        if(recipe != null){
            recipe.setApproved(1);
            recipeRepository.save(recipe);
            logger.info("[approveRecipe] response : bool : "+true);
            return true;
        }else{
            logger.info("[approveRecipe] response : bool : "+false);
            return false;
        }
    }

    public Boolean rejectRecipe(Long id){
        logger.info("[rejectRecipe] argument : id : "+id);
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Recipe recipe = optionalRecipe.get();
        if(recipe != null){
            recipe.setApproved(0);
            recipe.setDeleted(1);
            recipeRepository.save(recipe);
            logger.info("[rejectRecipe] response : bool : "+true);
            return true;
        }else{
            logger.info("[rejectRecipe] response : bool : "+false);
            return false;
        }
    }

    public List<RecipeDto> getUnApprovedRecipes(){
        logger.info("[getUnApprovedRecipes]");
        List<RecipeDto> recipeDtos = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findByApprovedAndDeleted(0,0);
        for(Recipe recipe : recipes){
            recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
        }
        logger.info("[getUnApprovedRecipes] response : recipeDtos : "+recipeDtos.toString());
        return recipeDtos;
    }
    public List<RecipeDto> getRecipesByDishType(String dishType){
        logger.info("[getRecipesByDishType] argument : dishType : "+dishType);
        List<RecipeDto> recipeDtos = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findByDishTypeAndApprovedAndDeleted(dishType,1,0);
        for(Recipe recipe : recipes){
            recipeDtos.add(DtoConversion.convertRecipeToRecipeDto(recipe));
        }
        logger.info("[getRecipesByDishType] response : recipeDtos : "+recipeDtos.toString());
        return recipeDtos;
    }

}
