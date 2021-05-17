package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.Recipe;
import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.RecipeIngredientRepository;
import com.example.sumptuous.dao.RecipeRepository;
import com.example.sumptuous.dao.UserRepository;
import com.example.sumptuous.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RecipeIngredientRepository recipeIngredientRepository;

    @Test
    public void testSearchRecipes(){
        RecipeRequestUserDto recipeRequestUserDto = new RecipeRequestUserDto();
        recipeRequestUserDto.setName("recipe");
        recipeRequestUserDto.setDishType("salad");
        recipeRequestUserDto.setCookingTime(10);
        recipeRequestUserDto.setChecked(false);
        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(1L);
        ingredientDto1.setName("milk");
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        ingredientDtos.add(ingredientDto1);
        recipeRequestUserDto.setIngredientDtos(ingredientDtos);
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("recipe");
        recipe.setDishType("salad");
        recipe.setCookingTime(10);
        recipe.setDirections("[]");
        recipe.setIngredientsList("[]");
        recipes.add(recipe);
        Mockito.when(recipeRepository.searchByIngredientPatternAndMealTypeAndDishType("1|",null,"salad")).thenReturn(recipes);
        RecipeResponseUserDto recipeResponseUserDto = recipeService.searchRecipes(recipeRequestUserDto);
        System.out.println(recipeResponseUserDto.toString());
        assertThat(recipeResponseUserDto.getRecipeDtos().get(0).getName()).isEqualTo(recipeRequestUserDto.getName());
        assertThat(recipeResponseUserDto.getRecipeDtos().get(0).getDishType()).isEqualTo(recipeRequestUserDto.getDishType());
        assertThat(recipeResponseUserDto.getRecipeDtos().get(0).getCookingTime()).isEqualTo(recipeRequestUserDto.getCookingTime());

    }

    @Test
    public void testAddRecipe(){
        RecipeRequestDto recipeRequestDto = new RecipeRequestDto();
        recipeRequestDto.setName("recipe");
        recipeRequestDto.setDishType("salad");
        recipeRequestDto.setCookingTime(10);
        recipeRequestDto.setDirections("");
        recipeRequestDto.setIngredientsList("");
        recipeRequestDto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        Optional<User> optionalUser = Optional.of(user);
        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(1L);
        ingredientDto1.setName("milk");
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        ingredientDtos.add(ingredientDto1);
        recipeRequestDto.setIngredientDtos(ingredientDtos);
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        RecipeDto recipeDto = recipeService.addRecipe(recipeRequestDto);
        System.out.println(recipeDto.toString());
        assertThat(recipeDto.getName()).isEqualTo(recipeRequestDto.getName());
        assertThat(recipeDto.getDishType()).isEqualTo(recipeRequestDto.getDishType());
        assertThat(recipeDto.getCookingTime()).isEqualTo(recipeRequestDto.getCookingTime());

    }

    @Test
    public void testFindRecipeByName(){
        Recipe recipe = new Recipe();
        recipe.setName("recipe");
        recipe.setDishType("salad");
        recipe.setCookingTime(10);
        recipe.setDirections("[]");
        recipe.setIngredientsList("[]");
        Mockito.when(recipeRepository.findByName(Mockito.anyString())).thenReturn(recipe);
        assertThat(recipeService.findRecipeByName("recipe")).isEqualTo(true);
    }

    @Test
    public void testApproveRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("recipe");
        recipe.setDishType("salad");
        recipe.setCookingTime(10);
        recipe.setDirections("[]");
        recipe.setIngredientsList("[]");
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(optionalRecipe);
        assertThat(recipeService.approveRecipe(1L)).isEqualTo(true);
    }

    @Test
    public void testRejectRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("recipe");
        recipe.setDishType("salad");
        recipe.setCookingTime(10);
        recipe.setDirections("[]");
        recipe.setIngredientsList("[]");
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(optionalRecipe);
        assertThat(recipeService.rejectRecipe(1L)).isEqualTo(true);
    }

    @Test
    public void testGetUnapprovedRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("recipe");
        recipe.setDishType("salad");
        recipe.setCookingTime(10);
        recipe.setDirections("[]");
        recipe.setIngredientsList("[]");
        recipes.add(recipe);
        Mockito.when(recipeRepository.findByApprovedAndDeleted(0,0)).thenReturn(recipes);
        List<RecipeDto> recipeDtos = recipeService.getUnApprovedRecipes();
        assertThat(recipeDtos.get(0).getName()).isEqualTo(recipe.getName());
        assertThat(recipeDtos.get(0).getDishType()).isEqualTo(recipe.getDishType());
        assertThat(recipeDtos.get(0).getCookingTime()).isEqualTo(recipe.getCookingTime());
    }


}
