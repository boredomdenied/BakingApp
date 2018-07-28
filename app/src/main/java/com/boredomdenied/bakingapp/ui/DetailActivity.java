package com.boredomdenied.bakingapp.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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

    private StepAdapter stepAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        if(getIntent().hasExtra("recipes"))  {
                    final Recipe recipe = getIntent().getParcelableExtra("recipes");
            Toast.makeText(DetailActivity.this, "has recipes", Toast.LENGTH_SHORT).show();
            Toast.makeText(DetailActivity.this, recipe.getName(), Toast.LENGTH_SHORT).show();


        } else  {
            Toast.makeText(DetailActivity.this, "no recipes", Toast.LENGTH_SHORT).show();

        }

        //        final Recipe recipe = getIntent().getParcelableExtra("recipes");


//        Log.d("recipes: ", (String.valueOf(recipe.getId())));

//        Toast.makeText(DetailActivity.this, (String.valueOf(recipe.getId())), Toast.LENGTH_SHORT).show();

//        int recipeId = getIntent().getIntExtra("recipe", 1) - 1;

//        List<Step> steps = recipe.getSteps();
//        recyclerView = findViewById(R.id.stepRecyclerView);
//        stepAdapter = new StepAdapter(this, steps);
//        RecyclerView.LayoutManager layoutManagers = new LinearLayoutManager(DetailActivity.this);
//        recyclerView.setLayoutManager(layoutManagers);
//        recyclerView.setAdapter(stepAdapter);

    }

//    /*Method to generate List of data using RecyclerView with custom adapter*/
//    private void generateDataList(List<Recipe> recipeList) {
//
//        List<Recipe> recipes = getIntent().getParcelableExtra("recipes");
//        int recipeId = getIntent().getIntExtra("recipe", 1) - 1;
//
//        List<Step> steps = recipes.get(recipeId).getSteps();
//        recyclerView = findViewById(R.id.stepRecyclerView);
//        stepAdapter = new StepAdapter(this, steps);
//        RecyclerView.LayoutManager layoutManagers = new LinearLayoutManager(DetailActivity.this);
//        recyclerView.setLayoutManager(layoutManagers);
//        recyclerView.setAdapter(stepAdapter);
//
//
//    }




}
