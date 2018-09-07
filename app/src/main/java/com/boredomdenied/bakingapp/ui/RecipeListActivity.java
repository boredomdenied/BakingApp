package com.boredomdenied.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.adapter.StepAdapter;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements StepAdapter.StepItemClickListener {


    private boolean mTwoPane;
    private TextView ingredients;
    private TextView step;
    private List<Recipe> recipes;
    private List<Step> stepList;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        step = findViewById(R.id.step);


        if (findViewById(R.id.recipe_detail_container) != null) {
            mTwoPane = true;
        }


        recipes = getIntent().getParcelableArrayListExtra("recipe");
        index = getIntent().getIntExtra("index", 0);


        List<Ingredient> ingredientList = recipes.get(index).getIngredients();

        ingredients = findViewById(R.id.ingredients);
        ingredients.setText("Ingredents:" + "\n\n");

        for (int i = 0; i < ingredientList.size(); i++) {
            ingredients.append(ingredientList.get(i).getIngredient() + "\n");
        }


        View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

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
        stepList = recipes.get(index).getSteps();
        recyclerView.setAdapter(new StepAdapter(this, stepList, this));
    }


    @Override
    public void onStepItemClick(int clickedItemIndex) {


        if (mTwoPane) {
            stepList = recipes.get(index).getSteps();
            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList("stepList", (ArrayList<? extends Parcelable>) stepList);
            arguments.putInt("index", clickedItemIndex);
            VideoStepsFragment fragment = new VideoStepsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_detail_container, fragment)
                    .commit();

            step.setText("");
            step.setText(stepList.get(clickedItemIndex).getDescription());


        } else {
            stepList = recipes.get(index).getSteps();
            Intent intent = new Intent(this, RecipeDetailActivity.class);
            intent.putParcelableArrayListExtra("recipeSteps", (ArrayList<? extends Parcelable>) stepList);
            intent.putExtra("stepIndex", clickedItemIndex);
            this.startActivity(intent);

        }

    }

}