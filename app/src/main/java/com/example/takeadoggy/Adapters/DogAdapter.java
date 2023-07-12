package com.example.takeadoggy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takeadoggy.Interfaces.DogCallback;
import com.example.takeadoggy.MainActivity;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.R;
import com.example.takeadoggy.Utilities.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private Context context;
    private ArrayList<Dog> dogs;
    private DogCallback dogCallback;

    public DogAdapter(Context context, ArrayList<Dog> dogs) {
        this.context = context;
        this.dogs = dogs;
    }

    public void setDogCallback(DogCallback dogCallback) {
        this.dogCallback = dogCallback;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("passed VT:", "" + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        DogViewHolder dogViewHolder = new DogViewHolder(view);
        return dogViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        Dog dog = getItem(position);
        holder.dog_LBL_name.setText(dog.getName() + ", ");
        holder.dog_LBL_age.setText(Double.toString(dog.getAge()) + ", ");
        holder.dog_LBL_breed.setText(dog.getBreed() + ", ");
        holder.dog_LBL_sex.setText(dog.getSex() + "");
        holder.dog_LBL_size.setText(dog.getSize() + "");
        holder.dog_LBL_attributes[0].setText(dog.getAttribute1() + "");
        holder.dog_LBL_attributes[1].setText(dog.getAttribute2() + "");
        holder.dog_LBL_restrictions[0].setText(dog.getRestriction1() + "");
        holder.dog_LBL_restrictions[1].setText(dog.getRestriction2() + "");

        ImageLoader.getInstance().loadImage(dog.getImage(), holder.dog_IMG_pic);

        checkIfUserDogFavourite(dog);

        if (dog.isFavourite())
            holder.dog_IMG_favorite.setImageResource(R.drawable.heart_full);
        else
            holder.dog_IMG_favorite.setImageResource(R.drawable.heart_empty);
    }

    private void checkIfUserDogFavourite(Dog dog) {
        int size = MainActivity.favDogsFromDB.size();

        for (int i = 0; i < size; i++) {
            String favName = MainActivity.favDogsFromDB.get(i).getName();
            getSingleDogName(favName);
        }
    }

    private void getSingleDogName(String favName) {
        int size = dogs.size();

        for (int i = 0; i < size; i++) {
            if(dogs.get(i).getName().equalsIgnoreCase(favName))
                dogs.get(i).setFavourite(true);

        }
    }


    @Override
    public int getItemCount() {
        return this.dogs == null ? 0 : dogs.size();
    }

    private Dog getItem(int position) {
        return this.dogs.get(position);
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView dog_IMG_favorite;
        private MaterialTextView dog_LBL_name;
        private MaterialTextView dog_LBL_age;
        private MaterialTextView dog_LBL_breed;
        private MaterialTextView dog_LBL_size;
        private MaterialTextView dog_LBL_sex;
        private MaterialTextView[] dog_LBL_attributes;
        private MaterialTextView[] dog_LBL_restrictions;
        private MaterialButton dog_BTN_adopt;
        private ShapeableImageView dog_IMG_pic;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            dog_IMG_favorite = itemView.findViewById(R.id.dog_IMG_favorite);
            dog_BTN_adopt = itemView.findViewById(R.id.dog_BTN_adopt);
            dog_LBL_age = itemView.findViewById(R.id.dog_LBL_age);
            dog_LBL_name = itemView.findViewById(R.id.dog_LBL_name);
            dog_LBL_breed = itemView.findViewById(R.id.dog_LBL_breed);
            dog_LBL_sex = itemView.findViewById(R.id.dog_LBL_sex);
            dog_LBL_size = itemView.findViewById(R.id.dog_LBL_size);
            dog_LBL_attributes = new MaterialTextView[] {itemView.findViewById(R.id.dog_LBL_att1),
                                                        itemView.findViewById(R.id.dog_LBL_att2)};
            dog_LBL_restrictions = new MaterialTextView[] {itemView.findViewById(R.id.dog_LBL_restr1),
                    itemView.findViewById(R.id.dog_LBL_restr2)};
            dog_IMG_pic = itemView.findViewById(R.id.favdog_IMG_pic);

            dog_IMG_favorite.setOnClickListener(v -> {
                if (dogCallback != null)
                    dogCallback.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
            });
            dog_BTN_adopt.setOnClickListener(view -> {dogCallback.adoptClicked(getAdapterPosition());});
        }
    }
}
