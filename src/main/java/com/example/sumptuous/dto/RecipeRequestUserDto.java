package com.example.sumptuous.dto;

import java.util.List;

public class RecipeRequestUserDto {
    private List<IngredientDto> ingredientDtos;
    private String mealType;
    private String dishType;
    private String name;
    private Integer cookingTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
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
                ", name='" + name + '\'' +
                ", cookingTime=" + cookingTime +
                ", checked=" + checked +
                '}';
    }
}
