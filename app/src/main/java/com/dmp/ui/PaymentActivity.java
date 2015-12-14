package com.dmp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dmp.R;
import com.dmp.bus.RequestPaymentBus;
import com.dmp.bus.ResponseErrorPaymentBus;
import com.dmp.bus.ResponsePaymentBus;
import com.dmp.model.Payment;
import com.dmp.model.Product;
import com.dmp.utils.Util;

import java.util.List;

import de.greenrobot.event.EventBus;

public class PaymentActivity extends AppCompatActivity {
    Payment mPay;
    List<Product> mProducts;
    TextView mTvTotalPrice;
    TextView mTvTotalItens;
    EditText mEdtCardNumberFragment1;
    EditText mEdtCardNumberFragment2;
    EditText mEdtCardNumberFragment3;
    EditText mEdtCardNumberFragment4;
    EditText mEdtCVV;
    EditText mEdtName;
    EditText mEdtCardYear;
    EditText mEdtCardMonth;
    Button mBtnPay;
    View.OnClickListener mPayListener;
    Context mContext;
    EventBus mBus = EventBus.getDefault();
    Snackbar snackLoading;
    Snackbar snackSuccess;
    Snackbar snackError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        mProducts = (List<Product>) getIntent().getSerializableExtra(Util.PARAM_PRODUCTS);
        mPay = new Payment();
        mPay.price = getTotalValue(mProducts);
        loadListeners();
        loadView();
        mBus.register(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private double getTotalValue(List<Product> products) {

        double totalPrice = 0;
        for (Product product : products) {
            totalPrice = totalPrice + product.price;

        }
        return totalPrice;
    }

    private void loadListeners() {
        mPayListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hasAllData()) {
                    showLoadingSnackbar();
                    RequestPaymentBus requestPaymentBus = new RequestPaymentBus();
                    requestPaymentBus.payment = getPaymennt();
                    requestPaymentBus.context = mContext;
                    mBus.post(requestPaymentBus);
                } else {
                    Snackbar.make(v, R.string.payment_error_msg_validator, Snackbar.LENGTH_SHORT).show();
                }


            }
        };
    }

    private void showLoadingSnackbar() {
        snackLoading.show();

    }

    private Payment getPaymennt() {

        mPay.cardName = mEdtName.getText().toString();
        mPay.cvv = mEdtCVV.getText().toString();
        mPay.dtCard = mEdtCardMonth.getText().toString() + "/" + mEdtCardYear.getText().toString();
        mPay.cardNumber = mEdtCardNumberFragment1.getText().toString() + mEdtCardNumberFragment2.getText().toString() + mEdtCardNumberFragment3.getText().toString() + mEdtCardNumberFragment4.getText().toString();
        return mPay;
    }

    private boolean hasAllData() {
        return !TextUtils.isEmpty(mEdtCardMonth.getText()) &&
                !TextUtils.isEmpty(mEdtCardNumberFragment1.getText()) &&
                !TextUtils.isEmpty(mEdtCardNumberFragment2.getText()) &&
                !TextUtils.isEmpty(mEdtCardNumberFragment3.getText()) &&
                !TextUtils.isEmpty(mEdtCardNumberFragment4.getText()) &&
                !TextUtils.isEmpty(mEdtCardYear.getText()) &&
                !TextUtils.isEmpty(mEdtCVV.getText()) &&
                !TextUtils.isEmpty(mEdtName.getText()) && !TextUtils.isEmpty(mPay.cardFlagName);
    }

    private void loadView() {
        mTvTotalItens = (TextView) findViewById(R.id.payment_total_qtd);
        mTvTotalPrice = (TextView) findViewById(R.id.payment_total_price);
        mEdtCardMonth = (EditText) findViewById(R.id.payment_card_month);
        mEdtCardNumberFragment1 = (EditText) findViewById(R.id.payment_edt_card_fragment_1);
        mEdtCardNumberFragment2 = (EditText) findViewById(R.id.payment_edt_card_fragment_2);
        mEdtCardNumberFragment3 = (EditText) findViewById(R.id.payment_edt_card_fragment_3);
        mEdtCardNumberFragment4 = (EditText) findViewById(R.id.payment_edt_card_fragment_4);
        mEdtCVV = (EditText) findViewById(R.id.payment_edt_secury_code);
        mEdtName = (EditText) findViewById(R.id.payment_name_card);
        mEdtCardYear = (EditText) findViewById(R.id.payment_card_year);
        mBtnPay = (Button) findViewById(R.id.payment_btn_buy);
        snackLoading = Snackbar.make(mBtnPay, R.string.payment_snack_loading, Snackbar.LENGTH_INDEFINITE);
        snackSuccess = Snackbar.make(mBtnPay, R.string.payment_snack_success, Snackbar.LENGTH_SHORT);
        snackError = Snackbar.make(mBtnPay, R.string.payment_snack_error, Snackbar.LENGTH_SHORT);

        mTvTotalItens.setText(String.valueOf(mProducts.size()));
        mTvTotalPrice.setText(String.valueOf(mPay.price));
        mBtnPay.setOnClickListener(mPayListener);

    }


    public void cardFlagClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.payment_radio_visa:
                if (checked) {
                    mPay.cardFlagId = 1;
                    mPay.cardFlagName = getString(R.string.payment_card_flag_visa);
                }
                break;
            case R.id.payment_radio_master:
                if (checked) {
                    mPay.cardFlagId = 2;
                    mPay.cardFlagName = getString(R.string.payment_card_flag_mastercard);
                }
                break;
            case R.id.payment_radio_amex:
                if (checked) {
                    mPay.cardFlagId = 3;
                    mPay.cardFlagName = getString(R.string.payment_card_flag_amex);
                }
                break;
        }
    }

    //Events
    public void onEvent(ResponsePaymentBus responsePaymentBus) {
        snackLoading.dismiss();
        snackSuccess.show();
        snackSuccess.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                finish();
            }
        });
    }

    public void onEvent(ResponseErrorPaymentBus responseErrorPaymentBus) {
        snackLoading.dismiss();
        snackError.show();
    }
}
