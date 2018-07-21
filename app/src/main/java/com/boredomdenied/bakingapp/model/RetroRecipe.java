
package com.boredomdenied.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroRecipe {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("servings")
    private String servings;
    @SerializedName("image")
    private String image;
    @SerializedName("ingredients")
    private List<Ingredient> ingredients = null;
    @SerializedName("steps")
    private List<Step> steps = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RetroRecipe() {
    }

    /**
     *
     * @param id
     * @param name
     * @param image
     * @param servings
     */
    public RetroRecipe(String id, List<Ingredient> ingredients, List<Step> steps, String name, String image, String servings) {
        super();
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}