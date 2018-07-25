package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.CustomViewHolder> {

    private List<Step> stepList;
    private Context context;

    public StepAdapter(Context context, List<Step> stepList){
        this.stepList = stepList;
        this.context = context;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {


        public final View mView;

        TextView txtName;
        TextView txtServings;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
        }
    }

    @Override
    public StepAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new StepAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.CustomViewHolder holder, int position) {

        Step step = stepList.get(position);
//        holder.txtId.setText(ingredient.getQuantity());
//        holder.txtIngredients.setText(String.valueOf((ingredient.getIngredients().size())));
//        holder.txtIngredients.setText(String.valueOf((ingredient.getIngredients())));
        holder.txtName.setText(String.valueOf((step.getDescription())));




    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }
}
