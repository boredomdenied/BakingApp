package com.boredomdenied.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;

import com.boredomdenied.bakingapp.adapter.RecipeDetailAdapter;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements RecipeDetailAdapter.StepItemClickListener {


    private boolean mTwoPane;
    private TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.recipe_detail_container) != null) {

            mTwoPane = true;
        }

        /*
        *   We need to populate the ingredients TextView
        */

        final List<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipe");
        final int index = getIntent().getIntExtra("index", 0);
        List<Ingredient> ingredientList = recipes.get(index).getIngredients();

        ingredients = findViewById(R.id.ingredients);

        ingredients.setText("Ingredents:" + "\n\n");

        for (int i = 0; i < ingredientList.size(); i++) {
        ingredients.append(ingredientList.get(i).getIngredient() + "\n");
        }


        View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


        if(getIntent().hasExtra("recipe")) {
            Toast.makeText(this, "Have Recipe: " + recipes.get(index).getName(), Toast.LENGTH_SHORT).show();
            Log.d("RecipeList:", recipes.get(index).getName());

        } else {
            Toast.makeText(this, "No RecipeList", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        final List<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipe");
        final int index = getIntent().getIntExtra("index", 0);
        List<Step> stepList = recipes.get(index).getSteps();

        recyclerView.setAdapter(new RecipeDetailAdapter(this, stepList, this));
    }

    @Override
    public void onStepItemClick(int clickedItemIndex) {
        final List<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipe");
        final int index = getIntent().getIntExtra("index", 0);
        List<Step> stepList = recipes.get(index).getSteps();

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putParcelableArrayListExtra("recipeSteps", (ArrayList<? extends Parcelable>) stepList);
        intent.putExtra("stepIndex", clickedItemIndex);
        this.startActivity(intent);
    }



}