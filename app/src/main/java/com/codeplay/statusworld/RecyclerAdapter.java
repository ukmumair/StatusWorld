package com.codeplay.statusworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VHolder> {
    Context context;
    List<ModelMovies> movies;

    public RecyclerAdapter(Context context, List<ModelMovies> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHolder(LayoutInflater.from(context).inflate(R.layout.recview_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        holder.textView.setText(movies.get(position).title);
        Glide.with(context)
                .load(movies.get(position).thumbnail)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class VHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView2);
        }
    }
}
