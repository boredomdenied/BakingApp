package com.boredomdenied.bakingapp.model;

import com.google.gson.annotations.SerializedName;


public class RetroRecipe {

    @SerializedName("id")
    private String recipeId;
    @SerializedName("name")
    private String recipeName;

    public RetroRecipe(String recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
