package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybaccaratapp.src.controller.Dealer;
import com.example.mybaccaratapp.src.model.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LoanActivity extends AppCompatActivity implements View.OnClickListener{

    Button applyButton;
    TextView confirmTxtView;
    TextView walletTxtView;
    TextView debtTxtView;
    Button settleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        applyButton = findViewById(R.id.applyButton);
        confirmTxtView = findViewById(R.id.confirmationTxtView);
        walletTxtView = findViewById(R.id.walletTxtView);
        debtTxtView = findViewById(R.id.debtTxtView);
        settleButton = findViewById(R.id.settleButton);

        applyButton.setOnClickListener(this);
        settleButton.setOnClickListener(this);

        loansInit();
    }

    @Override
    public void onClick(View v)
    {
        double debtVal = 0.00;
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

        switch (v.getId())
        {
            case R.id.applyButton:
                if (User.wallet.intValue() > 20)
                    Toast.makeText( this, "You do not qualify for a loan", Toast.LENGTH_SHORT).show();
                else
                {
                    switch (User.status)
                    {
                        case 0:
                            debtVal = 500.00;
                            User.setWallet(debtVal);
                            Dealer.myHouse.setUserDebt(debtVal);
                            break;

                        case 1:
                            debtVal = 2500.00;
                            User.setWallet(debtVal);
                            Dealer.myHouse.setUserDebt(debtVal);
                            break;

                        case 2:
                            debtVal = 50000.00;
                            User.setWallet(debtVal);
                            Dealer.myHouse.setUserDebt(debtVal);
                            break;

                        case 3:
                            debtVal = 250000.00;
                            User.setWallet(debtVal);
                            Dealer.myHouse.setUserDebt(debtVal);
                    }
                    confirmTxtView.setText("Based on your status,\n" +
                            "you have been approved for a\n" +
                            formatter.format( debtVal ) + " loan.\n\n" +
                            "Don't make us bust your knees....");

                    loansInit();
                }
                break;

            case R.id.settleButton:
                if (User.wallet.doubleValue() > Dealer.myHouse.userDebt.doubleValue() /*&& Dealer.myHouse.userDebt.doubleValue() != 0*/)
                {
                    User.wallet = new BigDecimal( String.valueOf( User.wallet.doubleValue() - Dealer.myHouse.userDebt.doubleValue() ));
                    Dealer.myHouse.zeroDebt();
                    loansInit();
                    Toast.makeText( this, "どうもありがとう", Toast.LENGTH_SHORT).show();
                }
                else if (Dealer.myHouse.userDebt.doubleValue() == 0)
                    Toast.makeText( this, "No debt to settle", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText( this, "Cannot settle debt", Toast.LENGTH_SHORT).show();
        }

    }

    public void loansInit()
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");
        walletTxtView.setText("Wallet: " +  formatter.format( User.wallet ));
        debtTxtView.setText("Debt: " +  formatter.format( Dealer.myHouse.userDebt ));
    }
}
