package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybaccaratapp.src.controller.Dealer;
import com.example.mybaccaratapp.src.model.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RecordsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView bankView;
    TextView walletView;
    TextView debtView;
    TextView statusView;
    Button hiScoreButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_a);

        bankView = findViewById(R.id.bank);
        walletView = findViewById(R.id.wallet);
        debtView = findViewById(R.id.debt);
        statusView = findViewById(R.id.status);
        hiScoreButton = findViewById(R.id.hiScoreButton);
        resetButton = findViewById(R.id.resetButton);

        hiScoreButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        initRecords();
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch ( v.getId() )
        {
            case R.id.hiScoreButton:
                intent = new Intent( this, HiScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.resetButton:
                Dealer.reset();
                initRecords();
                Toast.makeText( this, "New session created", Toast.LENGTH_SHORT).show();
                //this.finish();
        }
    }

    private void initRecords()
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

        bankView.setText( formatter.format( Dealer.myHouse.houseTotal ) );
        walletView.setText( formatter.format( User.wallet ));
        debtView.setText( formatter.format( Dealer.myHouse.userDebt ) );
        switch (User.status)
        {
            case 0:
                statusView.setText( "Low-level");
                break;
            case 1:
                statusView.setText( "Mid-level");
                break;
            case 2:
                statusView.setText( "High-level");
                break;
            case 3:
                statusView.setText( "Whale");

        }
    }
}
