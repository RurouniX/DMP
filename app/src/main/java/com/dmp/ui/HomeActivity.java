package com.dmp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.dmp.R;
import com.dmp.bus.TstBus;
import com.dmp.model.Product;
import com.dmp.ui.adapter.AdapterHomeProducts;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class HomeActivity extends AppCompatActivity {
    EventBus mBus = EventBus.getDefault();
    Context mContext;
    RecyclerView mRecProduct;
    private View.OnClickListener mFabListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        loadListeners();
        loadView();
    }

    private void loadListeners() {
       mFabListener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TstBus d = new TstBus();
                d.context = mContext;
                mBus.post(d);
                Snackbar.make(view, R.string.home_buy_msg_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };
    }

    private void loadView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecProduct = (RecyclerView) findViewById(R.id.home_rec_products);
        mRecProduct.setAdapter(new AdapterHomeProducts(mContext, R.layout.adapter_home_products, new ArrayList<Product>()));
        mRecProduct.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(mFabListener);
    }

}
