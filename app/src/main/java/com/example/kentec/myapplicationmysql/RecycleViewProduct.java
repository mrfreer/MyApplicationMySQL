package com.example.kentec.myapplicationmysql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dfreer on 11/15/2017.
 */

public class RecycleViewProduct extends RecyclerView.Adapter<RecycleViewProduct.ViewHolder> {

    Context context;
    private String id;
    List<Product> products;


    public RecycleViewProduct(List<Product> getDataAdapter, Context context, String id){
        super();
        this.products = getDataAdapter;
        this.context = context;
        this.id = id;
    }


    @Override
    public RecycleViewProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecycleViewProduct.ViewHolder holder, int position) {
        Product getDataAdapter = products.get(position);
        holder.ProductName.setText(getDataAdapter.getName());
        holder.ProductDescription.setText(getDataAdapter.getDescription());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView ProductName, ProductDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ProductName = (TextView) itemView.findViewById(R.id.textViewProdName);
            ProductDescription = (TextView) itemView.findViewById(R.id.textViewProdDesc);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.v("clicked", "testing");

        }
    }
}
