package com.example.sumptuous.dto;

import java.util.List;

public class RecipeRequestDto {

    private String name;
    private String directions;
    private String videoLink;
    private int cookingTime;
    private String dishType;
    private String mealType;
    private int serves;
    private String imageUrl;
    private String ingredientsList;
    private List<IngredientDto> ingredientDtos;
    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<IngredientDto> getIngredientDtos() {
        return ingredientDtos;
    }

    public void setIngredientDtos(List<IngredientDto> ingredientDtos) {
        this.ingredientDtos = ingredientDtos;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RecipeRequestDto{" +
                "name='" + name + '\'' +
                ", directions='" + directions + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", cookingTime=" + cookingTime +
                ", dishType='" + dishType + '\'' +
                ", mealType='" + mealType + '\'' +
                ", serves=" + serves +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredientsList='" + ingredientsList + '\'' +
                ", ingredientDtos=" + ingredientDtos +
                '}';
    }
}
