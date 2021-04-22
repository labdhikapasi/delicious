package com.example.sumptuous.dao;

import com.example.sumptuous.bean.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("Select ing from Ingredient ing where ing.name like %:name%")
    List<Ingredient> searchByName(@Param("name") String name);

    List<Ingredient> findAll();
}
