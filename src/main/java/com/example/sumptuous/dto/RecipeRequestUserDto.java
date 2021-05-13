package com.example.sumptuous.dto;

import java.util.List;

public class RecipeRequestUserDto {
    private List<IngredientDto> ingredientDtos;
    private String mealType;
    private String dishType;
    private Boolean checked;

    public List<IngredientDto> getIngredientDtos() {
        return ingredientDtos;
    }

    public void setIngredientDtos(List<IngredientDto> ingredientDtos) {
        this.ingredientDtos = ingredientDtos;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "RecipeRequestUserDto{" +
                "ingredientDtos=" + ingredientDtos +
                ", mealType='" + mealType + '\'' +
                ", dishType='" + dishType + '\'' +
                ", checked=" + checked +
                '}';
    }
}
