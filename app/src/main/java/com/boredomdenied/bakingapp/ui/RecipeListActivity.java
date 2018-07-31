package com.boredomdenied.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;

import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity  {

    private boolean mTwoPane;


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

        View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        final List<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipe");
        final int index = getIntent().getIntExtra("index", 0);


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

        List<Ingredient> ingredientList = recipes.get(index).getIngredients();
        List<Step> stepList = recipes.get(index).getSteps();
        recipeListAdapter.StepItemClickListener onClickListener = null;

        recyclerView.setAdapter(new recipeListAdapter(this, ingredientList, stepList, onClickListener));
    }

    @Override
    public void onStepItemClick(int clickedItemIndex) {
        final List<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipe");
        final int index = getIntent().getIntExtra("index", 0);

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putParcelableArrayListExtra("recipe", (ArrayList<? extends Parcelable>) recipes);
        intent.putExtra("index", clickedItemIndex);
        Log.d("onListItemClick", "onClick: clicked on " + recipes.get(clickedItemIndex).getId());

        this.startActivity(intent);
    }

    public static class recipeListAdapter extends RecyclerView.Adapter<recipeListAdapter.CustomViewHolder> {

        private List<Step> stepList;
        private List<Ingredient> ingredientList;
        private Context context;
        private StepItemClickListener onClickListener;

        public interface StepItemClickListener {
            void onStepItemClick(int clickedItemIndex);
        }


        public recipeListAdapter(Context context, List<Ingredient> ingredientList,
                                 List<Step> stepList, recipeListAdapter.StepItemClickListener listener){
            this.onClickListener = listener;
            this.ingredientList = ingredientList;
            this.stepList = stepList;
            this.context = context;
        }


        @Override
        public recipeListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
            return new recipeListAdapter.CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            Step step = stepList.get(position);
            Ingredient ingredient = ingredientList.get(position);
            holder.txtServings.setText(step.getDescription());
            holder.txtName.setText(String.valueOf((ingredient.getIngredient())));


        }

        @Override
        public int getItemCount() {
            return ingredientList.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            public final View mView;

            TextView txtName;
            TextView txtServings;

            CustomViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                txtName = mView.findViewById(R.id.name);
                txtServings = mView.findViewById(R.id.servings);

                itemView.setOnClickListener(this);
                }

            @Override
            public void onClick(View v) {

                int clickedPosition = getAdapterPosition();
                onClickListener.onStepItemClick(clickedPosition);
            }        }

    }



}