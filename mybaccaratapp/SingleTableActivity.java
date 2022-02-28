package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.example.mybaccaratapp.src.controller.Dealer;
import com.example.mybaccaratapp.src.model.*;

import static com.example.mybaccaratapp.src.controller.Rules.totalHand;
import static com.example.mybaccaratapp.src.view.Display.*;

public class SingleTableActivity extends AppCompatActivity implements View.OnClickListener {

    private int dev_mode_counter;

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
    TextView maxMinTxtView;

    public static final String EMPTY_STRING = "";
    public static final String ZERO_DOUBLE = "0.00";
    public static final String PLAYER_LOWER = "player";
    public static final String TIE_LOWER = "tie";
    public static final String BANKER_LOWER = "banker";
    public static final String MARU = "〇";

    public static final String NO_MONEY = "You have no money";
    public static final String UNMET_REQ = "You cannot meet table bet requirements";
    public static final String ENTER_BET = "Please select bet amount";
    public static final String ENTER_WHO = "Please choose bet selection";
    public static final String TABLE_BUST = "TABLE BUST!";
    public static final String WINNER = "すごいじゃん\n\t\t＼(^_^)／\n  勝ちました";

    private NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_table);

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
        maxMinTxtView = findViewById(R.id.maxMinTxtView);

        init();
    }

    @Override
    public void onClick(View v)
    {
        switch ( v.getId() )
        {
            case R.id.playButton:
                if ( (Dealer.userSelection.isEmpty() && Dealer.userBet == Double.parseDouble(ZERO_DOUBLE) ||
                        (!Dealer.userSelection.isEmpty()&& Dealer.userBet != Double.parseDouble(ZERO_DOUBLE))) )
                {
                    winner = Dealer.runGame();

                    switch (winner)
                    {
                        case PLAYER_LOWER:
                            winnerDisplay.setText("Player wins with a total of " + totalHand(Player.myHand) );
                            break;
                        case BANKER_LOWER:
                            winnerDisplay.setText("Banker wins with a total of " + totalHand(Banker.myHand) );
                            break;
                        case TIE_LOWER:
                            winnerDisplay.setText("The game is a tie.");
                    }
                    walletTxtView.setText(formatter.format(User.wallet));
                    tableTxtView.setText(formatter.format(Dealer.myTable.tableTotal));
                    finishHand();
                }
                else
                {
                    if (Dealer.userBet == Double.parseDouble(ZERO_DOUBLE))
                        Toast.makeText( this, ENTER_BET, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText( this, ENTER_WHO, Toast.LENGTH_SHORT).show();
                }
                dev_mode_counter = 0;
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
                //--------------------------
                if ( dev_mode_counter == 19)
                {
                    Toast.makeText( this, "いいじゃん", Toast.LENGTH_LONG).show();
                    User.dev_mode();
                }

                if ( dev_mode_counter == 29 )
                {
                    HiScore.clearScores();
                    Toast.makeText( this, "リセット", Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                //--------------------------
                resetSelectionButtons();
                ++dev_mode_counter;
                break;

            case R.id.playerBetButton:
                if ( Dealer.userSelection.equals(PLAYER_LOWER))
                    maxBet();
                Dealer.userSelection = PLAYER_LOWER;
                clearWho();
                playerBet.setText(MARU);
                break;

            case R.id.tieBetButton:
                if ( Dealer.userSelection.equals(TIE_LOWER))
                    maxBet();
                Dealer.userSelection = TIE_LOWER;
                clearWho();
                tieBet.setText(MARU);
                break;

            case R.id.bankerBetButton:
                if ( Dealer.userSelection.equals(BANKER_LOWER))
                    maxBet();
                Dealer.userSelection = BANKER_LOWER;
                clearWho();
                bankerBet.setText(MARU);
        }
    }

    public void init()
    {
        setButtonDefaults();

        dev_mode_counter = 0;
        Dealer.userSelection = EMPTY_STRING;
        Dealer.userBet = Double.parseDouble(ZERO_DOUBLE);
        walletTxtView.setText(formatter.format(User.wallet));
        tableTxtView.setText(formatter.format(Dealer.myTable.tableTotal));
        userBetTxtView.setText(formatter.format(Double.parseDouble(ZERO_DOUBLE)));

        checkUserWallet();
    }

    public void setButtonDefaults()
    {
        bet0.setText( formatBets(Dealer.myTable.bet0) );
        bet1.setText( formatBets(Dealer.myTable.bet1) );
        bet2.setText( formatBets(Dealer.myTable.bet2) );
        bet3.setText( formatBets(Dealer.myTable.bet3) );
        maxMinTxtView.setText("Max: "+formatBets(Dealer.myTable.tableMax.intValue())+"\n"
                +"Min: "+formatBets(Dealer.myTable.tableMin.intValue()) );
    }

    public void finishHand()
    {
        showCards();
        Dealer.clear();

        if (Dealer.isTableMax())
        {
            if (Dealer.myHouse.houseTotal.intValue() > 0)//not whale table
            {
                Toast.makeText( this, TABLE_BUST, Toast.LENGTH_SHORT).show();
                if ( User.status < Dealer.myTable.stakesLevel )
                    ++User.status;
            }
            else
            {
                Intent intent;
                HiScore.addScore( HiScore.calcScore() );
                CasinoFloorA.saveButton.performClick();
                Toast.makeText( this, WINNER, Toast.LENGTH_LONG).show();

                if ( HiScore.calcScore() > 0 )
                {
                    intent = new Intent( this, HiScoreActivity.class );
                    startActivity(intent);
                    intent = new Intent( this, WinnerActivity.class );
                    startActivity(intent);
                }
                else
                {
                    intent = new Intent( this, LoserActivity.class);
                    startActivity(intent);
                }

                Dealer.reset();
            }

            this.finish();
        }
    }

    public void showCards()
    {
        playerHand.setText( getHand(Player.myHand) );
        bankerHand.setText( getHand(Banker.myHand) );
        checkUserWallet();
        resetSelectionButtons();
    }

    public void resetSelectionButtons()
    {
        Dealer.userBet = Double.parseDouble(ZERO_DOUBLE);
        userBetTxtView.setText( formatter.format( Dealer.userBet ) );
        Dealer.userSelection = EMPTY_STRING;
        clearWho();
    }

    public void clearWho()
    {
        playerBet.setText(PLAYER_LOWER);
        tieBet.setText(TIE_LOWER);
        bankerBet.setText(BANKER_LOWER);
    }

    public void checkUserWallet()
    {
        if ( User.wallet.intValue() < 1 )
        {
            Toast.makeText( this, NO_MONEY, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent( this, LoanActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if ( User.wallet.intValue() < Dealer.myTable.tableMin.intValue() )
        {
            Toast.makeText( this, UNMET_REQ, Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public void maxBet()
    {
        Dealer.userBet = Dealer.myTable.tableMax;
        Dealer.checkBetBounds();
        userBetTxtView.setText( formatter.format(Dealer.userBet) );
    }
}
