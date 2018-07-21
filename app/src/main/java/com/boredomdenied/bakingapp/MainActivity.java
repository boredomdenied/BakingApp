package com.boredomdenied.bakingapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.adapter.IngredientAdapter;
import com.boredomdenied.bakingapp.adapter.RecipeAdapter;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.RetroRecipe;
import com.boredomdenied.bakingapp.network.GetDataService;
import com.boredomdenied.bakingapp.network.GetDataIngredient;
import com.boredomdenied.bakingapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter adapter;
    private IngredientAdapter adapters;

    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<RetroRecipe>> recipe = service.getRecipe();
        recipe.enqueue(new Callback<List<RetroRecipe>>() {

            @Override
            public void onResponse(Call<List<RetroRecipe>> call, Response<List<RetroRecipe>> response) {
                progressDoalog.dismiss();
                generateRecipeList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroRecipe>> call, Throwable t) {
                progressDoalog.dismiss();

                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

//        /*Create handle for the RetrofitInstance interface*/
//        GetDataIngredient services = RetrofitClientInstance.getRetrofitInstance().create(GetDataIngredient.class);
//
//        Call<List<Ingredient>> ingredient = services.getIngredient();
//        ingredient.enqueue(new Callback<List<Ingredient>>() {
//
//            @Override
//            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
//                progressDoalog.dismiss();
//                generateIngredientList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
//                progressDoalog.dismiss();
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateRecipeList(List<RetroRecipe> recipeList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new RecipeAdapter(this,recipeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateIngredientList(List<Ingredient> ingredientList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapters = new IngredientAdapter(this, ingredientList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapters);
    }


}
