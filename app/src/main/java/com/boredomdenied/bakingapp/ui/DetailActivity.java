package com.boredomdenied.bakingapp.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.adapter.IngredientAdapter;
import com.boredomdenied.bakingapp.adapter.StepAdapter;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.model.Step;
import com.boredomdenied.bakingapp.network.GetDataService;
import com.boredomdenied.bakingapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

    private IngredientAdapter ingredientAdapter;
    private StepAdapter stepAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressDoalog = new ProgressDialog(DetailActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Recipe>> call = service.getAllData();
        call.enqueue(new Callback<List<Recipe>>() {

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(DetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Recipe> recipeList) {

        int recipeId = getIntent().getIntExtra("recipe", 1) - 1;
//        List<Ingredient> ingredients = recipeList.get(recipeId).getIngredients();
//        recyclerView = findViewById(R.id.ingredientRecyclerView);
//        ingredientAdapter = new IngredientAdapter(this, ingredients);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(ingredientAdapter);

//        int recipeId = getIntent().getIntExtra("recipe", 1) - 1;
        List<Step> steps = recipeList.get(recipeId).getSteps();
        recyclerView = findViewById(R.id.stepRecyclerView);
        stepAdapter = new StepAdapter(this, steps);
        RecyclerView.LayoutManager layoutManagers = new LinearLayoutManager(DetailActivity.this);
        recyclerView.setLayoutManager(layoutManagers);
        recyclerView.setAdapter(stepAdapter);


    }




}
