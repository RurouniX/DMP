package com.dmp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dmp.R;
import com.dmp.bus.AddItemOnCartBus;
import com.dmp.bus.RequestProductsBus;
import com.dmp.bus.ResponseErrorProductBus;
import com.dmp.bus.ResponseProductsBus;
import com.dmp.model.Product;
import com.dmp.ui.adapter.AdapterHomeProducts;
import com.dmp.utils.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class HomeActivity extends AppCompatActivity {
    EventBus mBus = EventBus.getDefault();
    Context mContext;
    RecyclerView mRecProduct;
    private View.OnClickListener mFabListener;
    private View mHomeContent;
    private View mErrorLayout;
    private View mLoadingLayout;
    private Button mBtnTryAgain;
    private View.OnClickListener mTryAgainListener;
    private List<Product> mListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        mBus.register(this);
        loadListeners();
        loadView();
        mBus.post(new RequestProductsBus());
    }

    private void loadListeners() {
        mFabListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListProduct == null || mListProduct.isEmpty()) {
                    Snackbar.make(view, R.string.home_buy_msg_error, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Intent intent = new Intent(mContext, PaymentActivity.class);
                    intent.putExtra(Util.PARAM_PRODUCTS, (Serializable) mListProduct);
                    startActivity(intent);
                }
            }
        };
        mTryAgainListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLayout();
                mBus.post(new RequestProductsBus());
            }
        };
    }

    private void loadView() {
        mHomeContent = findViewById(R.id.home_content);
        mErrorLayout = findViewById(R.id.home_layout_error);
        mLoadingLayout = findViewById(R.id.loading_loading);
        mBtnTryAgain = (Button) findViewById(R.id.error_btn_try_again);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecProduct = (RecyclerView) findViewById(R.id.home_rec_products);
        mRecProduct.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(mFabListener);
        mBtnTryAgain.setOnClickListener(mTryAgainListener);
    }

    private void showSuccessLayout() {
        mHomeContent.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mLoadingLayout.setVisibility(View.GONE);
    }

    private void showErrorLayout() {
        mHomeContent.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mLoadingLayout.setVisibility(View.GONE);
    }

    private void showLoadingLayout() {
        mHomeContent.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    //Events
    public void onEvent(ResponseProductsBus responseProductsBus) {
        mRecProduct.setAdapter(new AdapterHomeProducts(mContext, R.layout.adapter_home_products, responseProductsBus.products));
        showSuccessLayout();

    }

    public void onEvent(ResponseErrorProductBus responseErorrProductsBus) {
        showErrorLayout();

    }

    public void onEvent(AddItemOnCartBus addItemOnCartBus) {

        if (mListProduct == null) {
            mListProduct = new ArrayList<>();
        }

        Product product = addItemOnCartBus.product;
        mListProduct.add(product);

        Snackbar.make(mHomeContent, getString(R.string.home_snack_msg).replace("##", product.productName), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

}
