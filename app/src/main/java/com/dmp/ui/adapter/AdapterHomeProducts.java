package com.dmp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmp.R;
import com.dmp.model.Product;

import java.util.List;

/**
 * Created by X-Dream on 13/12/15.
 */
public class AdapterHomeProducts extends RecyclerView.Adapter<AdapterHomeProducts.ViewHolder> {

    Context mContext;
    int mLayoutId;
    List<Product> mProductList;

    public AdapterHomeProducts(Context context, int layoutID, List<Product> productList) {
        mContext = context;
        mLayoutId = layoutID;
        mProductList = productList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTest.setText("sdfghn");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTest;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTest = (TextView) itemView.findViewById(R.id.txtst);
        }
    }
}
