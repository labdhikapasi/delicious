package com.example.sumptuous.bean;

import com.example.sumptuous.enums.DishType;
import com.example.sumptuous.enums.MealType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    @NonNull
    @Column(columnDefinition = "json")
    //@Convert(attributeName = "data",converter= JsonArrayConverter.class)
    private String directions;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "cooking_time")
    private int cookingTime;

    @Column(name = "avg_rating")
    private int avgRating = 0;

    //@Enumerated(EnumType.STRING)
    @Column(name = "dish_type")
    private String dishType;

    //@Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "ingredients_list", columnDefinition = "json")
    private String ingredientsList;

    private int serves;

    @Column(name = "image_url")
    private String imageUrl;

    @JsonIgnoreProperties("recipe")
    @OneToMany(mappedBy = "recipe")
    Set<RecipeIngredient> recipeIngredients;

    @Column(name = "ingredient_pattern")
    private String ingredientPattern;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "update_by", referencedColumnName = "id")
    private User updatedBy;

    private int approved;

    private int deleted;

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getDirections() {
        return directions;
    }

    public void setDirections(@NonNull String directions) {
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

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
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

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getIngredientPattern() {
        return ingredientPattern;
    }

    public void setIngredientPattern(String ingredientPattern) {
        this.ingredientPattern = ingredientPattern;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", directions='" + directions + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", cookingTime=" + cookingTime +
                ", avgRating=" + avgRating +
                ", dishType='" + dishType + '\'' +
                ", mealType='" + mealType + '\'' +
                ", ingredientsList='" + ingredientsList + '\'' +
                ", serves=" + serves +
                ", imageUrl='" + imageUrl + '\'' +
                ", recipeIngredients=" + recipeIngredients +
                ", ingredientPattern='" + ingredientPattern + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", approved=" + approved +
                ", deleted=" + deleted +
                '}';
    }
}
