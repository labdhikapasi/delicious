package com.example.sumptuous.dao;

import com.example.sumptuous.bean.Ingredient;
import com.example.sumptuous.bean.RecipeIngredient;
import com.example.sumptuous.bean.RecipeIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientKey> {

}
