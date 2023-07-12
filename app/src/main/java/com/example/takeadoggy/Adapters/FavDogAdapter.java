package com.example.takeadoggy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takeadoggy.Interfaces.OnItemClickListener;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.R;
import com.example.takeadoggy.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class FavDogAdapter extends RecyclerView.Adapter<FavDogAdapter.FavDogViewHolder>{

    private Context context;
    private ArrayList<Dog> dogs;

    private OnItemClickListener listener;

    public FavDogAdapter(Context context, ArrayList<Dog> dogs, OnItemClickListener listener) {
        this.context = context;
        this.dogs = dogs;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FavDogAdapter.FavDogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("passed VT:", "" + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_dog_item, parent, false);
        FavDogAdapter.FavDogViewHolder dogViewHolder = new FavDogAdapter.FavDogViewHolder(view);
        return dogViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavDogAdapter.FavDogViewHolder holder, int position) {
        Dog dog = getItem(position);
        holder.favdog_LBL_name.setText(dog.getName() + "");
        holder.favdog_LBL_breed.setText(dog.getBreed() + "");

        ImageLoader.getInstance().loadImage(dog.getImage(), holder.favdog_IMG_pic);
    }

    @Override
    public int getItemCount() {
        return this.dogs == null ? 0 : dogs.size();
    }

    private Dog getItem(int position) {
        return this.dogs.get(position);
    }

    public class FavDogViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView favdog_LBL_name;
        private MaterialTextView favdog_LBL_breed;
        private ShapeableImageView favdog_IMG_pic;

        public FavDogViewHolder(@NonNull View itemView) {
            super(itemView);

            favdog_LBL_name = itemView.findViewById(R.id.favdog_LBL_name);
            favdog_LBL_breed = itemView.findViewById(R.id.favdog_LBL_breed);
            favdog_IMG_pic = itemView.findViewById(R.id.favdog_IMG_pic);
            itemView.setOnClickListener(v -> {
                if(listener != null)
                    listener.itemClicked(getAdapterPosition());
            });
        }
    }

}
