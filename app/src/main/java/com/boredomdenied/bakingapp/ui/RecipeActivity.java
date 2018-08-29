package com.boredomdenied.bakingapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.adapter.RecipeAdapter;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.network.GetDataService;
import com.boredomdenied.bakingapp.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener {

    private RecipeAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    public List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        progressDialog = new ProgressDialog(RecipeActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Recipe>> call = service.getAllData();
        call.enqueue(new Callback<List<Recipe>>() {

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
                recipeList = response.body();

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateDataList(List<Recipe> recipeList) {

        recyclerView = findViewById(R.id.customRecyclerView);
        assert recyclerView != null;
        adapter = new RecipeAdapter(this, recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {


                Intent intent = new Intent(this, RecipeListActivity.class);
                intent.putParcelableArrayListExtra("recipe", (ArrayList<? extends Parcelable>) recipeList);
                intent.putExtra("index", clickedItemIndex);
                        Log.d("onListItemClick", "onClick: clicked on " + recipeList.get(clickedItemIndex).getId());

        this.startActivity(intent);



    }




//    private String getAndFormatIngredients(List<Recipe> recipeList) {
//
//
//        RecipeIngredient[] recipeIngredients = recipeList.get(1).getIngredients();
//        String[] ingredients = new String[recipeIngredients.length];
//        for (int i = 0; i < ingredients.length; i++) {
//            ingredients[i] = recipeIngredients[i].getQuantityUnitNameString();
//
//
//        }
//        return TextUtils.join("\n", ingredients);
//    }
}
