package com.example.sumptuous.dao;

import com.example.sumptuous.bean.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIngredientPattern(String ingredientPattern);
}
