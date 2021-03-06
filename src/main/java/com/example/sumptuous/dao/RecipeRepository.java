package com.example.sumptuous.dao;

import com.example.sumptuous.bean.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByIngredientPattern(String ingredientPattern);
    Recipe findByName(String name);
    List<Recipe> findByApprovedAndDeleted(int approved, int deleted);
    List<Recipe> findByDishTypeAndApprovedAndDeleted(String dishType, int approved, int deleted);
    @Query("select r from Recipe r where r.ingredientPattern = :ingredientPattern and r.mealType like %:mealType and r.dishType like %:dishType and approved=1 and deleted=0")
    List<Recipe> searchByIngredientPatternAndMealTypeAndDishType(@Param("ingredientPattern") String ingredientPattern, @Param("mealType") String mealType, @Param("dishType") String dishType);

    @Query("select r from Recipe r where r.ingredientPattern <> :ingredientPattern and r.mealType like %:mealType and r.dishType like %:dishType and approved=1 and deleted=0")
    List<Recipe> searchByNotIngredientPatternAndMealTypeAndDishType(@Param("ingredientPattern") String ingredientPattern, @Param("mealType") String mealType, @Param("dishType") String dishType);

    @Query("select r from Recipe r where r.mealType like %:mealType and r.dishType like %:dishType and r.name like %:name% and r.cookingTime = :cookingTime and approved=1 and deleted=0")
    List<Recipe> searchByMealTypeAndDishTypeAndNameAndCookingTime(@Param("mealType") String mealType,@Param("dishType") String dishType, @Param("name") String name, @Param("cookingTime") Integer cookingTime);

    @Query("select r from Recipe r where r.mealType like %:mealType and r.dishType like %:dishType and r.name like %:name% and approved=1 and deleted=0")
    List<Recipe> searchByMealTypeAndDishTypeAndName(@Param("mealType") String mealType,@Param("dishType") String dishType, @Param("name") String name);

}
