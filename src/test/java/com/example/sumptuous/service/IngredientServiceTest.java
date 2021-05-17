package com.example.sumptuous.service;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.IngredientRepository;
import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;

    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    public void testGetIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("milk");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("butter");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        Mockito.when(ingredientRepository.findAll()).thenReturn(ingredients);
        assertThat(ingredientService.getIngredients().size()).isEqualTo(ingredients.size());
    }

    @Test
    public void testAddIngredient(){
        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(1L);
        ingredientDto1.setName("milk");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("milk");

        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenReturn(ingredient1);
        assertThat(ingredientService.addIngredient(ingredientDto1).getName()).isEqualTo(ingredientDto1.getName());
    }

    @Test
    public void testCheckIngredientByName(){
        String name = "recipe";

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("recipe");

        Mockito.when(ingredientRepository.findByName(name)).thenReturn(ingredient1);
        assertThat(ingredientService.checkIngredientByName(name)).isEqualTo(true);
    }
}
