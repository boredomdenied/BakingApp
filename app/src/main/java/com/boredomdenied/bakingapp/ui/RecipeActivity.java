package com.boredomdenied.bakingapp.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.adapter.IngredientAdapter;
import com.boredomdenied.bakingapp.adapter.RecipeAdapter;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.network.GetDataService;
import com.boredomdenied.bakingapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener {

    private RecipeAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private Toast toast;
    private Recipe recipe;
    public List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        progressDoalog = new ProgressDialog(RecipeActivity.this);
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
                Toast.makeText(RecipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Recipe> recipeList) {

        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new RecipeAdapter(this, recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex, List<Recipe> recipeList) {
        if (toast != null) {
            toast.cancel();
        }

                Intent intent = new Intent(this, RecipeListActivity.class);
                intent.putExtra("recipe", (Parcelable) recipeList);
                this.startActivity(intent);


        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        toast.show();
    }
}
