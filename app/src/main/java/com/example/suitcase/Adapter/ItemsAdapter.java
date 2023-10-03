package com.example.suitcase.Adepter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suitcase.ItemsModel;
import com.example.suitcase.R;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private final com.example.suitcase.Adapter.RecyclerItemsClickView recyclerItemsClickView;
    private ArrayList<ItemsModel> itemsModels;

    public ItemsAdapter(ArrayList<ItemsModel>itemsModels, com.example.suitcase.Adapter.RecyclerItemsClickView recyclerItemsClickView){
        this.recyclerItemsClickView=recyclerItemsClickView;
        this.itemsModels=itemsModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {

        ItemsModel itemsModel=itemsModels.get(position);
        holder.textViewName.setText(itemsModel.getName());
        if (itemsModel.isPurchased()){
            holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_add_24,0);

        }
        holder.textViewprice.setText(String.valueOf(itemsModel.getPrice()));
        holder.textViewDescription.setText(itemsModel.getDescription());
        Uri imageUri=itemsModel.getImage();
        if (imageUri!=null){
            holder.imageView.setImageURI(imageUri);

        }


    }

    @Override
    public int getItemCount() {

        return itemsModels.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName,textViewprice,textViewDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.item_image);
            textViewName=itemView.findViewById(R.id.item_Name);
            textViewprice=itemView.findViewById(R.id.item_price);
            textViewDescription=itemView.findViewById(R.id.item_Dis);

            itemView.setOnClickListener(this::itemViewOnClick);
        }

        private void itemViewOnClick(View view) {
            recyclerItemsClickView.onItemClick(view,getAdapterPosition());

        }
    }
}