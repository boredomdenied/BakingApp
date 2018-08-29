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

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.CustomViewHolder> {

    private List<Step> stepList;
    private Context context;
    private StepItemClickListener onClickListener;


    public interface StepItemClickListener {
        void onStepItemClick(int clickedItemIndex);
    }


    public RecipeDetailAdapter(Context context,
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
        holder.mShortDescription.setText(String.valueOf((step.getShortDescription())));

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final View mView;

        TextView mShortDescription;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mShortDescription = mView.findViewById(R.id.id_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            onClickListener.onStepItemClick(clickedPosition);

        }

    }

}

