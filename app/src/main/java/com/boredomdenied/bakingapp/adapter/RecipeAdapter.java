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

import static java.lang.String.valueOf;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> dataList;
    private Context context;
    private ListItemClickListener onClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public RecipeAdapter(Context context, List<Recipe> dataList, ListItemClickListener listener){
        this.onClickListener = listener;
        this.dataList = dataList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtName.setText(dataList.get(position).getName());
        holder.txtServings.setText(String.valueOf(dataList.get(position).getServings()) + " servings");

//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int ID = dataList.get(position).getId();
//                Log.d(TAG, "onClick: clicked on " + dataList.get(position).getId());
//                Intent intent = new Intent(context, RecipeListActivity.class);
//                intent.putExtra("recipe", ID);
//                context.startActivity(intent);
//
//             }
//        };
//        holder.mView.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;

        TextView txtServings;
        TextView txtName;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtServings = mView.findViewById(R.id.servings);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
        }
    }

}
