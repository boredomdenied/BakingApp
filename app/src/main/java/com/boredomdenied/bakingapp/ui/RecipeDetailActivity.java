package com.boredomdenied.bakingapp.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;


public class RecipeDetailActivity extends AppCompatActivity {
    private TextView step;
    private List<Step> stepList;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        step = findViewById(R.id.step);

        initialOrientation();

        index = getIntent().getIntExtra("stepIndex", 0);
        stepList = getIntent().getParcelableArrayListExtra("recipeSteps");

        if (savedInstanceState == null) {


            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList("stepList", (ArrayList<? extends Parcelable>) stepList);
            arguments.putInt("index", index);
            VideoPlayerFragment fragment = new VideoPlayerFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, fragment)
                    .commit();

            step = findViewById(R.id.step);
            step.setText(stepList.get(index).getDescription());

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        checkOrientation(newConfig);
    }

    private void checkOrientation(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            step.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);

            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }

            step.setVisibility(View.VISIBLE);

        }
    }

    private void initialOrientation() {
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {

            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            step.setVisibility(View.GONE);

        } else if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {

            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);

            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }

            step.setVisibility(View.VISIBLE);
        }

    }
}