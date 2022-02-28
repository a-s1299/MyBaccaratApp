package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mybaccaratapp.src.controller.Dealer;
import com.example.mybaccaratapp.src.model.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    Button playerBet;
    Button tieBet;
    Button bankerBet;
    String winner;
    TextView winnerDisplay;
    TextView playerHand;
    TextView bankerHand;
    Button bet0;
    Button bet1;
    Button bet2;
    Button bet3;
    Button clearBet;
    TextView userBetTxtView;
    Button playButton;
    TextView walletTxtView;
    TextView tableTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_0a);

        playButton = findViewById(R.id.playButton);
        winnerDisplay = findViewById(R.id.winnerDisplay);
        playerHand = findViewById(R.id.playerHandDisplay);
        bankerHand = findViewById(R.id.bankerHandDisplay);
        walletTxtView = findViewById(R.id.userWalletTxtView);
        tableTxtView = findViewById(R.id.tableTotalTxtView);
        bet0 = findViewById(R.id.betButton0);
        bet1 = findViewById(R.id.betButton1);
        bet2 = findViewById(R.id.betButton2);
        bet3 = findViewById(R.id.betButton3);
        clearBet = findViewById(R.id.clearButton);
        playerBet = findViewById(R.id.playerBetButton);
        tieBet = findViewById(R.id.tieBetButton);
        bankerBet = findViewById(R.id.bankerBetButton);
        playButton.setOnClickListener(this);
        bet0.setOnClickListener(this);
        bet1.setOnClickListener(this);
        bet2.setOnClickListener(this);
        bet3.setOnClickListener(this);
        clearBet.setOnClickListener(this);
        playerBet.setOnClickListener(this);
        tieBet.setOnClickListener(this);
        bankerBet.setOnClickListener(this);
        userBetTxtView = findViewById(R.id.userBetTxtView);

        init();
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState)
//    {
//        super.onSaveInstanceState(savedInstanceState);
//
//        //savedInstanceState.put~("myWhatever", value);
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState)
//    {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        //type variable = savedInstanceState.get~("myWhatever");
//    }

    @Override
    public void onClick(View v)
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

        switch ( v.getId() )
        {
            case R.id.playButton:
                if ( (Dealer.userSelection == "" && Dealer.userBet == 0.00) ||
                        (Dealer.userSelection != "" && Dealer.userBet != 0.00) )
                {
                    winner = Dealer.runGame();

                    switch (winner)
                    {
                        case "player":
                            winnerDisplay.setText("Player wins with a total of " + com.example.mybaccaratapp.src.controller.Rules.totalHand(Player.myHand) );
                            break;
                        case "banker":
                            winnerDisplay.setText("Banker wins with a total of " + com.example.mybaccaratapp.src.controller.Rules.totalHand(Banker.myHand) );
                            break;
                        case "tie":
                            winnerDisplay.setText("The game is a tie.");
                    }
                    walletTxtView.setText(formatter.format(User.wallet));
                    tableTxtView.setText(formatter.format(Dealer.myTable.tableTotal));
                    finishHand();
                }
                else
                {
                    if (Dealer.userBet == 0.00)
                        Toast.makeText( this, "Please select amount to bet", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText( this, "Please select who you are betting on", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.betButton0:
                Dealer.userBet += Dealer.myTable.bet0;
                Dealer.checkBetBounds();
                userBetTxtView.setText( formatter.format(Dealer.userBet) );
                break;

            case R.id.betButton1:
                Dealer.userBet += Dealer.myTable.bet1;
                Dealer.checkBetBounds();
                userBetTxtView.setText( formatter.format(Dealer.userBet) );
                break;

            case R.id.betButton2:
                Dealer.userBet += Dealer.myTable.bet2;
                Dealer.checkBetBounds();
                userBetTxtView.setText( formatter.format(Dealer.userBet) );
                break;

            case R.id.betButton3:
                Dealer.userBet += Dealer.myTable.bet3;
                Dealer.checkBetBounds();
                userBetTxtView.setText( formatter.format(Dealer.userBet) );
                break;

            case R.id.clearButton:
                //User.wallet = new BigDecimal(50000); //all-access
                //User.status = 3;
                Dealer.userBet = 0.00;
                userBetTxtView.setText( formatter.format( Dealer.userBet ) );
                break;

            case R.id.playerBetButton:
                if ( Dealer.userSelection == "player" )
                    maxBet();
                Dealer.userSelection = "player";
                clearWho();
                playerBet.setText("〇");
                break;

            case R.id.tieBetButton:
                if ( Dealer.userSelection == "tie" )
                    maxBet();
                Dealer.userSelection = "tie";
                clearWho();
                tieBet.setText("〇");
                break;

            case R.id.bankerBetButton:
                if ( Dealer.userSelection == "banker" )
                    maxBet();
                Dealer.userSelection = "banker";
                clearWho();
                bankerBet.setText("〇");
        }
    }

    public void init()
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

        Dealer.myTable = new Table(0);
        Dealer.userSelection = "";
        Dealer.userBet = 0.00;
        walletTxtView.setText(formatter.format(User.wallet));
        tableTxtView.setText(formatter.format(Dealer.myTable.tableTotal));
        userBetTxtView.setText("$0.00");

        checkUserWallet();
    }

    public void finishHand()
    {
        showCards();
        Dealer.clear();

        if (Dealer.isTableMax())
        {
            Toast.makeText( this, "TABLE BUST!", Toast.LENGTH_SHORT).show();
            if ( User.status < 1 )
                ++User.status;
            this.finish();
        }
    }

    public void showCards()
    {
        playerHand.setText( com.example.mybaccaratapp.src.view.Display.getHand(Player.myHand) );
        bankerHand.setText( com.example.mybaccaratapp.src.view.Display.getHand(Banker.myHand) );
        userBetTxtView.setText("$0.00");
        checkUserWallet();
        resetSelectionButtons();
    }

    public void resetSelectionButtons()
    {
        Dealer.userBet = 0.00;
        Dealer.userSelection = "";
        clearWho();
    }

    public void clearWho()
    {
        playerBet.setText("Player");
        tieBet.setText("Tie");
        bankerBet.setText("Banker");
    }

    public void checkUserWallet()
    {
        if ( User.wallet.intValue() <= 1 )
        {
            Toast.makeText( this, "You have no money", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else if ( User.wallet.intValue() < Dealer.myTable.tableMin.intValue() )
        {
            Toast.makeText( this, "You cannot meet table bet requirements", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public void maxBet()
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");
        Dealer.userBet = Dealer.myTable.tableMax;
        Dealer.checkBetBounds();
        userBetTxtView.setText( formatter.format(Dealer.userBet) );
    }
}
