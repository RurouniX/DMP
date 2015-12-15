package com.dmp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmp.R;
import com.dmp.model.Payment;
import com.dmp.ui.HistoricActivity;

import java.util.Iterator;
import java.util.List;

/**
 * Created by X-Dream on 14/12/15.
 */
public class AdapterHistoricPayments extends RecyclerView.Adapter<AdapterHistoricPayments.ViewHolder> {

    List<Payment> mPaymentList;
    Context mContext;
    int mLayoutId;

    public AdapterHistoricPayments(Context context, int layoutId, List<Payment> paymentList) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mPaymentList = paymentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Payment payment = mPaymentList.get(position);
        holder.tvCardName.setText(payment.cardName);
        holder.tvCVV.setText(payment.cvv);
        holder.tvDtCard.setText(payment.dtCard);
        holder.tvFlagId.setText(String.valueOf(payment.cardFlagId));
        holder.tvFlagName.setText(payment.cardFlagName);
        holder.tvNumberCard.setText(payment.cardNumber);
        holder.tvPrice.setText(mContext.getString(R.string.adapter_currency) + payment.price);
    }

    @Override
    public int getItemCount() {
        return mPaymentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumberCard;
        TextView tvCVV;
        TextView tvFlagId;
        TextView tvFlagName;
        TextView tvPrice;
        TextView tvCardName;
        TextView tvDtCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCardName = (TextView) itemView.findViewById(R.id.adapter_historic_payment_card_name);
            tvNumberCard = (TextView) itemView.findViewById(R.id.adapter_historic_payment_card_number);
            tvCVV = (TextView) itemView.findViewById(R.id.adapter_historic_payment_cvv);
            tvFlagId = (TextView) itemView.findViewById(R.id.adapter_historic_payment_card_flag_id);
            tvFlagName = (TextView) itemView.findViewById(R.id.adapter_historic_payment_card_flag_name);
            tvPrice = (TextView) itemView.findViewById(R.id.adapter_historic_payment_price);
            tvDtCard = (TextView) itemView.findViewById(R.id.adapter_historic_payment_dt_card);
        }
    }
}
