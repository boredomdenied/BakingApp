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

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.CustomViewHolder> {

    private List<Ingredient> ingredientList;
    private Context context;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList){
        this.ingredientList = ingredientList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtId;
        TextView txtName;
        TextView txtIngredients;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
//            txtId = mView.findViewById(R.id.ids);
            txtIngredients = mView.findViewById(R.id.ids);
        }
    }

    @Override
    public IngredientAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new IngredientAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.CustomViewHolder holder, int position) {

        Ingredient ingredient = ingredientList.get(position);
//        holder.txtId.setText(ingredient.getId());
//        holder.txtIngredients.setText(String.valueOf((ingredient.getIngredients().size())));
//        holder.txtIngredients.setText(String.valueOf((ingredient.getIngredients())));
        holder.txtName.setText(ingredientList.get(position).getIngredient());



    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
