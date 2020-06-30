package com.example.newtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtask.entities.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarsHolder> {

    private List<Datum> list;
    private Context context;

    public CarAdapter(List<Datum> list ) {
        this.list = list;
    }


    @NonNull
    @Override
    public CarsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        context = itemView.getContext();
        return new CarsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsHolder holder, int position) {

            Datum current = list.get(position);
            holder.brand.setText(current.getBrand());

            if(current.getIsUsed())
                holder.used.setText("Used");
            else
                holder.used.setText("New");

            holder.construction.setText(current.getConstractionYear());

        Picasso.with(context).load(current.getImageUrl()).into(holder.image);





    }


    @Override
    public int getItemCount() {
            return list == null ? 0 : list.size();
    }

    class CarsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        AppCompatImageView image;
        @BindView(R.id.brand)
        TextView brand;
        @BindView(R.id.used)
        TextView used;
        @BindView(R.id.construction)
        TextView construction;
        @BindView(R.id.linearLayout)
        ConstraintLayout linearLayout;


        CarsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}


