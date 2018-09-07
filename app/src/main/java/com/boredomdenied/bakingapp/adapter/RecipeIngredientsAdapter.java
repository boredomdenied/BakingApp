package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Ingredient;

import java.util.List;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.CustomViewHolder> {

    private List<Ingredient> ingredientList;
    private Context context;
    private RecipeIngredientsAdapter.IngredientItemClickListener onClickListener;


    public RecipeIngredientsAdapter(Context context,
                                    List<Ingredient> ingredientList, RecipeIngredientsAdapter.IngredientItemClickListener listener) {
        this.onClickListener = listener;
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @Override
    public RecipeIngredientsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_list_content, parent, false);
        return new RecipeIngredientsAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeIngredientsAdapter.CustomViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.mShortDescription.setText(String.valueOf((ingredient.getIngredient())));

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public interface IngredientItemClickListener {
        void onIngredientItemClick(int clickedItemIndex);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final View mView;

        TextView mShortDescription;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mShortDescription = mView.findViewById(R.id.content);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            onClickListener.onIngredientItemClick(clickedPosition);

        }

    }

}

