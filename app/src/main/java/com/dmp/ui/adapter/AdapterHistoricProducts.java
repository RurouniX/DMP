package com.dmp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmp.R;
import com.dmp.model.Payment;
import com.dmp.model.Product;
import com.dmp.ui.HistoricActivity;

import java.util.Iterator;
import java.util.List;

/**
 * Created by X-Dream on 14/12/15.
 */
public class AdapterHistoricProducts extends RecyclerView.Adapter<AdapterHistoricProducts.ViewHolder> {

    List<Product> mProductList;
    Context mContext;
    int mLayoutId;

    public AdapterHistoricProducts(Context context, int layoutId, List<Product> productList) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mProductList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.tvName.setText(product.productName);
        holder.tvPrice.setText(mContext.getString(R.string.adapter_currency) + String.valueOf(product.price));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvName;
        TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.adapter_historic_product_name);
            tvPrice = (TextView) itemView.findViewById(R.id.adapter_historic_product_price);
        }
    }
}
