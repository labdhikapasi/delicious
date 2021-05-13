package com.example.sumptuous.dto;

import java.util.List;

public class RecipeResponseUserDto {
    private List<RecipeDto> recipeDtos;
    private List<RecipeDto> otherRecipeDtos;

    public List<RecipeDto> getRecipeDtos() {
        return recipeDtos;
    }

    public void setRecipeDtos(List<RecipeDto> recipeDtos) {
        this.recipeDtos = recipeDtos;
    }

    public List<RecipeDto> getOtherRecipeDtos() {
        return otherRecipeDtos;
    }

    public void setOtherRecipeDtos(List<RecipeDto> otherRecipeDtos) {
        this.otherRecipeDtos = otherRecipeDtos;
    }

    @Override
    public String toString() {
        return "RecipeResponseUserDto{" +
                "recipeDtos=" + recipeDtos +
                ", otherRecipeDtos=" + otherRecipeDtos +
                '}';
    }
}
