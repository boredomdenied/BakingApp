package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Recipe;
import com.boredomdenied.bakingapp.ui.DetailActivity;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static java.lang.String.valueOf;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private Recipe recipe;
    private Context context;


    public RecipeAdapter(Context context, List<Recipe> recipeList){
        this.recipeList = recipeList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtServings;
        TextView txtName;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtServings = mView.findViewById(R.id.servings);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtName.setText(recipeList.get(position).getName());
        holder.txtServings.setText(String.valueOf(recipeList.get(position).getServings()) + " servings");

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int ID = recipeList.get(position).getId();
                Log.d(TAG, "onClick: clicked on " + recipeList.get(position).getId());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("recipes", recipe);
//                intent.putExtra("recipe", ID);
                context.startActivity(intent);

             }
        };
        holder.mView.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
