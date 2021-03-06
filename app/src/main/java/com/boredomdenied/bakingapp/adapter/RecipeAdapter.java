package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private Context context;
    private ListItemClickListener onClickListener;


    public RecipeAdapter(Context context, List<Recipe> recipeList, ListItemClickListener listener) {
        this.onClickListener = listener;
        this.recipeList = recipeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.txtName.setText(recipeList.get(position).getName());
        holder.txtServings.setText(String.valueOf(recipeList.get(position).getServings()) + " servings");

        if (!TextUtils.isEmpty(recipeList.get(position).getImage())) {
            Picasso.get().load(recipeList.get(position).getImage())
                    .error(R.drawable.baking)
                    .placeholder(R.drawable.baking)
                    .into(holder.recipeImage);
        } else {
            Picasso.get().load(R.drawable.baking)
                    .into(holder.recipeImage);
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;

        TextView txtServings;
        TextView txtName;
        ImageView recipeImage;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtServings = mView.findViewById(R.id.servings);
            recipeImage = mView.findViewById(R.id.recipeImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
        }
    }
}
