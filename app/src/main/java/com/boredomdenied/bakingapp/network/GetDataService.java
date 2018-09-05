package com.boredomdenied.bakingapp.network;

import com.boredomdenied.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by praka on 12/24/2017.
 */

public interface GetDataService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getAllData();
}
