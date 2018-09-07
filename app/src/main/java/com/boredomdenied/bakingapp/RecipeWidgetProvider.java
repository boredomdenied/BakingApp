package com.boredomdenied.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.boredomdenied.bakingapp.model.Ingredient;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    private static ArrayList<Ingredient> ingredients;
    private static String recipe;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        if (recipe != null) {
            views.setTextViewText(R.id.appwidget_recipe, recipe);
        } else {
            views.setTextViewText(R.id.appwidget_recipe, "Recipe not yet viewed");
        }

        String ingredientList = "\n";

        if (ingredients != null) {
            for (Ingredient i : ingredients) {
                ingredientList += "\n" + i.getIngredient();
            }
        }
        views.setTextViewText(R.id.appwidget_ingredients, ingredientList);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.hasExtra("recipe") && intent.hasExtra("ingredients")) {
            recipe = intent.getStringExtra("recipe");
            ingredients = intent.getParcelableArrayListExtra("ingredients");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context.getPackageName(), RecipeWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }

    }
}

