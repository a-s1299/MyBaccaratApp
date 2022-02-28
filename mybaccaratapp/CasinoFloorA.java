package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mybaccaratapp.src.controller.Dealer;
import com.example.mybaccaratapp.src.model.HiScore;
import com.example.mybaccaratapp.src.model.House;
import com.example.mybaccaratapp.src.model.Table;
import com.example.mybaccaratapp.src.model.User;

import java.math.BigDecimal;


public class CasinoFloorA extends AppCompatActivity implements View.OnClickListener{

    Button tableButton0;
    Button tableButton1;
    Button tableButton2;
    Button tableButton3;
    Button recordsButton;
    Button loansButton;
    Button howToButton;
    public static Button saveButton;

    public static final String UNMET_REQ = "Requirement not yet met";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BANK = "bank";
    public static final String WALLET = "wallet";
    public static final String DEBT = "debt";
    public static final String STATUS = "status";
    public static final String DEFAULT_BANK = "10000000.00";
    public static final String DEFAULT_WALLET = "500.00";
    public static final String DEFAULT_DEBT = "0.00";
    public static final Integer DEFAULT_STATUS = 0;

    public static final String TOP_0 = "top0";
    public static final String TOP_1 = "top1";
    public static final String TOP_2 = "top2";
    public static final String TOP_3 = "top3";
    public static final String TOP_4 = "top4";
    public static final String TOP_5 = "top5";
    public static final String TOP_6 = "top6";
    public static final String TOP_7 = "top7";
    public static final String TOP_8 = "top8";
    public static final String TOP_9 = "top9";

    private String bank;
    private String wallet;
    private String debt;
    private int status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino_floor);

        tableButton0 = findViewById(R.id.table0aButton);
        tableButton1 = findViewById(R.id.table1aButton);
        tableButton2 = findViewById(R.id.table2aButton);
        tableButton3 = findViewById(R.id.table3aButton);
        recordsButton = findViewById(R.id.recordsButton);
        loansButton = findViewById(R.id.resetButton);
        howToButton = findViewById(R.id.howToButton);
        saveButton = findViewById(R.id.saveButton);

        tableButton0.setOnClickListener(this);
        tableButton1.setOnClickListener(this);
        tableButton2.setOnClickListener(this);
        tableButton3.setOnClickListener(this);
        recordsButton.setOnClickListener(this);
        loansButton.setOnClickListener(this);
        howToButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        casinoInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveData();
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch ( v.getId() )
        {
            case R.id.table0aButton:
                Dealer.myTable = new Table(1);
                intent = new Intent(this, SingleTableActivity.class );
                startActivity(intent);
                break;

            case R.id.table1aButton:


                if ( User.status < 1)
                    unmetReqToast();
                else
                {
                    Dealer.myTable = new Table(2);
                    intent = new Intent( this, SingleTableActivity.class );
                    startActivity(intent);
                }
                break;

            case R.id.table2aButton:
                if ( User.status < 2)
                    unmetReqToast();
                else
                {
                    Dealer.myTable = new Table(3);
                    intent = new Intent( this, SingleTableActivity.class );
                    startActivity(intent);
                }
                break;

            case R.id.table3aButton:
                if ( User.status < 3)
                    unmetReqToast();
                else
                {
                    Dealer.myTable = new Table(4);
                    intent = new Intent( this, SingleTableActivity.class );
                    startActivity(intent);
                }
                break;

            case R.id.recordsButton:
                intent = new Intent(this, RecordsActivity.class );
                startActivity(intent);
                break;

            case R.id.resetButton:
                intent = new Intent( this, LoanActivity.class );
                startActivity(intent);
                break;

            case R.id.howToButton:
                intent = new Intent( this, HowToActivity.class);
                startActivity(intent);
                break;

            case R.id.saveButton:
                Toast.makeText( this, "セーブしました", Toast.LENGTH_SHORT).show();
                break;
        }

        saveData();
    }

    public void casinoInit()
    {
        User.init();
        Dealer.myHouse = new House("a");
        Dealer.userSelection = "";
        HiScore.hiScoreInit();

        loadData();
        updateVals();
    }

    public void unmetReqToast()
    {
        Toast.makeText(this, UNMET_REQ, Toast.LENGTH_SHORT).show();
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString( BANK, Dealer.myHouse.houseTotal.toString() );
        editor.putString( WALLET, User.wallet.toString() );
        editor.putString( DEBT, Dealer.myHouse.userDebt.toString() );
        editor.putInt( STATUS, User.status );

        editor.putInt(TOP_0, HiScore.topTen.get(0));
        editor.putInt(TOP_1, HiScore.topTen.get(1));
        editor.putInt(TOP_2, HiScore.topTen.get(2));
        editor.putInt(TOP_3, HiScore.topTen.get(3));
        editor.putInt(TOP_4, HiScore.topTen.get(4));
        editor.putInt(TOP_5, HiScore.topTen.get(5));
        editor.putInt(TOP_6, HiScore.topTen.get(6));
        editor.putInt(TOP_7, HiScore.topTen.get(7));
        editor.putInt(TOP_8, HiScore.topTen.get(8));
        editor.putInt(TOP_9, HiScore.topTen.get(9));

        editor.apply();
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        bank = sharedPreferences.getString(BANK, DEFAULT_BANK);
        wallet = sharedPreferences.getString(WALLET, DEFAULT_WALLET);
        debt = sharedPreferences.getString(DEBT, DEFAULT_DEBT);
        status = sharedPreferences.getInt(STATUS, DEFAULT_STATUS);

        HiScore.topTen.add( 0, sharedPreferences.getInt(TOP_0, 0));
        HiScore.topTen.add( 1, sharedPreferences.getInt(TOP_1, 0));
        HiScore.topTen.add( 2, sharedPreferences.getInt(TOP_2, 0));
        HiScore.topTen.add( 3, sharedPreferences.getInt(TOP_3, 0));
        HiScore.topTen.add( 4, sharedPreferences.getInt(TOP_4, 0));
        HiScore.topTen.add( 5, sharedPreferences.getInt(TOP_5, 0));
        HiScore.topTen.add( 6, sharedPreferences.getInt(TOP_6, 0));
        HiScore.topTen.add( 7, sharedPreferences.getInt(TOP_7, 0));
        HiScore.topTen.add( 8, sharedPreferences.getInt(TOP_8, 0));
        HiScore.topTen.add( 9, sharedPreferences.getInt(TOP_9, 0));
    }

    public void updateVals()
    {
        Dealer.myHouse.houseTotal = new BigDecimal( bank );
        User.wallet = new BigDecimal( wallet );
        Dealer.myHouse.userDebt = new BigDecimal( debt );
        User.status = status ;
    }
}
