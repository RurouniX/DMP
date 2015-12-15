package com.dmp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ViewSwitcher;

import com.dmp.R;
import com.dmp.model.Payment;
import com.dmp.model.Product;
import com.dmp.ui.adapter.AdapterHistoricPayments;
import com.dmp.ui.adapter.AdapterHistoricProducts;

public class HistoricActivity extends AppCompatActivity {

    RecyclerView mRecPayment;
    RecyclerView mRecProducts;
    ViewSwitcher mViewSwitcher;
    boolean isPaymentShown = false;
    Button mBtnChangeView;
    View.OnClickListener mSwitcherListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadListener();
        loadView();
    }

    private void loadListener() {
        mSwitcherListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaymentShown) {
                    isPaymentShown = false;
                    mViewSwitcher.showNext();
                } else {
                    isPaymentShown = true;
                    mViewSwitcher.showPrevious();
                }


            }
        };
    }

    private void loadView() {

        mViewSwitcher = (ViewSwitcher) findViewById(R.id.historic_view_switcher);
        mRecPayment = (RecyclerView) findViewById(R.id.historic_payment);
        mRecPayment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecProducts = (RecyclerView) findViewById(R.id.historic_list_products);
        mRecProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecPayment.setAdapter(new AdapterHistoricPayments(this, R.layout.adapter_historic_payment, Payment.listAll(Payment.class)));
        mRecProducts.setAdapter(new AdapterHistoricProducts(this, R.layout.adapter_historic_products, Product.listAll(Product.class)));
        mBtnChangeView = (Button) findViewById(R.id.historic_change_historic);
        mBtnChangeView.setOnClickListener(mSwitcherListener);

    }

}
