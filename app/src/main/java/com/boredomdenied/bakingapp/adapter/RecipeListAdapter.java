package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Step;
import com.boredomdenied.bakingapp.ui.RecipeListActivity;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.CustomViewHolder> {

    private List<Step> stepList;
    private Context context;
    private StepItemClickListener onClickListener;


    public interface StepItemClickListener {
        void onStepItemClick(int clickedItemIndex);
    }


    public RecipeListAdapter(Context context,
                             List<Step> stepList, StepItemClickListener listener){
        this.onClickListener = listener;
        this.stepList = stepList;
        this.context = context;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_list_content, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Step step = stepList.get(position);
//        Ingredient ingredient = ingredientList.get(position);
        holder.txtServings.setText(step.getDescription());
//        holder.txtName.setText(String.valueOf((ingredient.getIngredient())));
        holder.txtName.setText(String.valueOf((step.getShortDescription())));


    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final View mView;

        TextView txtName;
        TextView txtServings;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.id_text);
            txtServings = mView.findViewById(R.id.content);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            onClickListener.onStepItemClick(clickedPosition);

        }

    }

}

