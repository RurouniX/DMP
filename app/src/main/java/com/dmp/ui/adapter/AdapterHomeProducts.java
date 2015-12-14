package com.dmp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmp.R;
import com.dmp.bus.AddItemOnCartBus;
import com.dmp.model.Product;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class AdapterHomeProducts extends RecyclerView.Adapter<AdapterHomeProducts.ViewHolder> {

    Context mContext;
    int mLayoutId;
    List<Product> mProductList;
    EventBus mBus = EventBus.getDefault();

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Product product = mProductList.get(position);

        holder.tvName.setText(product.productName);
        holder.tvPrice.setText(mContext.getString(R.string.adapter_currency) + product.price);
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemOnCartBus addItemOnCartBus = new AddItemOnCartBus();
                addItemOnCartBus.product = mProductList.get(position);
                mBus.post(addItemOnCartBus);
            }
        });
        Glide.with(mContext).load(product.thumb).into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPrice;
        ImageView imgThumb;
        Button btnBuy;

        public ViewHolder(View itemView) {
            super(itemView);
            btnBuy = (Button) itemView.findViewById(R.id.adapter_home_buy);
            tvPrice = (TextView) itemView.findViewById(R.id.adapter_home_products_price);
            tvName = (TextView) itemView.findViewById(R.id.adapter_home_products_name);
            imgThumb = (ImageView) itemView.findViewById(R.id.adapter_home_products_thumb);
        }
    }
}
