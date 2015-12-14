package com.dmp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.dmp.R;
import com.dmp.model.Paymment;

public class PaymmentActivity extends AppCompatActivity {
    Paymment mPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void cardFlagClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.paymment_radio_visa:
                if (checked)

                    break;
            case R.id.paymment_radio_master:
                if (checked)

                    break;
            case R.id.paymment_radio_amex:
                if (checked)

                    break;
        }
    }
}
