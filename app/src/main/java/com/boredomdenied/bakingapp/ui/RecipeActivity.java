package com.boredomdenied.bakingapp.ui;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.RecipeWidgetProvider;
import com.boredomdenied.bakingapp.adapter.RecipeAdapter;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.network.GetDataService;
import com.boredomdenied.bakingapp.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener {

    private List<Recipe> recipeList;
    private RecipeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Recipe>> call = service.getAllData();
        call.enqueue(new Callback<List<Recipe>>() {

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                generateDataList(response.body());
                recipeList = response.body();

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(RecipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateDataList(List<Recipe> recipeList) {

//        Toast.makeText(RecipeActivity.this, recipeList.toString(), Toast.LENGTH_SHORT).show();


        recyclerView = findViewById(R.id.customRecyclerView);
        assert recyclerView != null;
        adapter = new RecipeAdapter(this, recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

        List<Ingredient> ingredients = recipeList.get(clickedItemIndex).getIngredients();

        Intent widgetIntent = new Intent(this, RecipeWidgetProvider.class);
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        widgetIntent.putExtra("recipe", recipeList.get(clickedItemIndex).getName());
        widgetIntent.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) ingredients);


        Intent intent = new Intent(this, RecipeListActivity.class);
        intent.putParcelableArrayListExtra("recipe", (ArrayList<? extends Parcelable>) recipeList);
        intent.putExtra("index", clickedItemIndex);
        this.sendBroadcast(widgetIntent);
        this.startActivity(intent);


    }

}
