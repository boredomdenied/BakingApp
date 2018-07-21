
package com.boredomdenied.bakingapp.model;

import com.google.gson.annotations.SerializedName;

public class RetroRecipe {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("servings")
    private String servings;
    @SerializedName("image")
    private String image;

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
    public RetroRecipe(String id, String name, String image, String servings) {
        super();
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
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

}