package com.example.singelfragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.singelfragment.R;
import com.example.singelfragment.data.Example;
import com.example.singelfragment.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleHolder> {
    private ArrayList<Example> mExampleList;

    public ExampleAdapter(ArrayList<Example> mExampleList) {
        this.mExampleList = mExampleList;
    }

    @NotNull
    @Override
    public ExampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_view, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleHolder holder, int position) {
        Picasso.get().load(mExampleList.get(position).getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transform(new CircleTransform())
                .into(holder.ivIcon);
        holder.tvName.setText(mExampleList.get(position).getName());
        holder.tvEvent.setText(mExampleList.get(position).getEvent());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    class ExampleHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvEvent;

        ExampleHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.imageViewIcon);
            tvName = itemView.findViewById(R.id.textViewName);
            tvEvent = itemView.findViewById(R.id.textViewEvent);
        }
    }
}