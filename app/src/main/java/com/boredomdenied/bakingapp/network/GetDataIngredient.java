package com.boredomdenied.bakingapp.network;

import com.boredomdenied.bakingapp.model.Ingredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataIngredient {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Ingredient>> getIngredient();
}