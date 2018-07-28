package com.boredomdenied.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredomdenied.bakingapp.R;
import com.boredomdenied.bakingapp.model.Ingredient;
import com.boredomdenied.bakingapp.model.Step;
import com.boredomdenied.bakingapp.ui.DetailActivity;
import com.boredomdenied.bakingapp.ui.StepActivity;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

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
    public void onBindViewHolder(StepAdapter.CustomViewHolder holder, final int position) {

        Step step = stepList.get(position);
        holder.txtName.setText(String.valueOf((step.getShortDescription())));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String STEP = stepList.get(position).getDescription();
                String NEXT = stepList.get(position + 1).getDescription();
                String LAST = stepList.get(position - 1).getDescription();

                String VIDEO = stepList.get(position).getVideoURL();
                Log.d(TAG, "onClick: clicked on " + stepList.get(position).getId());
                Intent intent = new Intent(context, StepActivity.class);
                intent.putExtra("step", STEP);
                intent.putExtra("next", NEXT);
                intent.putExtra("last", LAST);
                intent.putExtra("video", VIDEO);
                context.startActivity(intent);

            }
        };
        holder.mView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }
}
