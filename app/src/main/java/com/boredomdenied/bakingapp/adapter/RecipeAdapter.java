package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CustomViewHolder> {

    private List<Recipe> dataList;
    private Context context;

    public RecipeAdapter(Context context, List<Recipe> dataList){
        this.dataList = dataList;
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
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Recipe retroRecipe = dataList.get(position);
//        holder.txtId.setText(retroRecipe.getId());
        holder.txtIngredients.setText(retroRecipe.getIngredients().size());
        holder.txtName.setText(dataList.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
