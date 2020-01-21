package com.example.recyclerviewretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Contact> newlist;
    private Context context;

    public Adapter(List<Contact> newlist, Context context) {
        this.newlist = newlist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // Get position of MyViewHolder class
        holder.name.setText(newlist.get(position).getName());
        holder.email.setText(newlist.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return newlist.size();
    }

    // This ViewHolder of RecycleView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email;
        public MyViewHolder(View itemView) {
            super(itemView);

            // Find ID of layout
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
