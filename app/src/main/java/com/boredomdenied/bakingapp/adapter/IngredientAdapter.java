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

    private List<Ingredient> dataList;
    private Context context;

    public IngredientAdapter(Context context, List<Ingredient> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtId;
        TextView txtName;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtId = mView.findViewById(R.id.ids);
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
        holder.txtName.setText(dataList.get(position).getIngredient());
        holder.txtId.setText(dataList.get(position).getQuantity());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
