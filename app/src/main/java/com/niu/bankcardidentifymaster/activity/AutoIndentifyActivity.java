package com.niu.bankcardidentifymaster.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.niu.bankcardidentifymaster.R;
import com.niu.bankcardidentifymaster.domain.BankCardInfo;
import com.niu.bankcardidentifymaster.util.CheckBankNameUtil;

/**
 * Created by niutingting on 2017/5/23.
 */

public class AutoIndentifyActivity extends AppCompatActivity {

    private TextInputLayout bankNumInput;
    private EditText etBankNum;
    private String bankText;
    private TextView tvBankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auto_identify);
        initView();
    }

    private void initView() {
        tvBankName = (TextView) findViewById(R.id.tv_bank_name);
        bankNumInput = (TextInputLayout) findViewById(R.id.input_bank_num);
        etBankNum = bankNumInput.getEditText();
        etBankNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bankText = editable.toString();
                if (bankText.length() == 10) {
                    checkBankName();
                }
            }
        });
    }

    /**
     * 识别银行卡名称
     */
    private void checkBankName() {
        BankCardInfo bankCardInfo = null;
        try {
            bankCardInfo = new
                    CheckBankNameUtil().getBankCardInfo(this, bankText);

            if (bankCardInfo == null) {//返回为null，代表没有识别到
                tvBankName.setText("没有识别到");
            } else {//识别到了
                String bankName = bankCardInfo.getBankName();
                String bankType = bankCardInfo.getCardType();
                String bankCode = bankCardInfo.getBankCode();
                String cardTypeName = bankCardInfo.getCardTypeName();
                tvBankName.setText("bankName:" + bankName + "---bankCode:" + bankCode
                        + "---bankType:" + bankType + "---cardTypeName:" + cardTypeName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
