package com.example.mybaccaratapp.src.controller;

import com.example.mybaccaratapp.src.model.*;
import java.util.Vector;


public class Dealer
{
    public static House myHouse;
    public static Table myTable;
    public static double userBet;
    public static String userSelection;

    public static String runGame()
    {
        String winner;
        Integer playerTotal;
        Integer bankerTotal;

        deal();
        playerTotal = Rules.totalHand(Player.myHand);
        bankerTotal = Rules.totalHand(Banker.myHand);

        if ( (Rules.isNatural(playerTotal)) || Rules.isNatural(bankerTotal))
        {
            if ( playerTotal.intValue() == bankerTotal.intValue() )
                winner = "tie";
            else if (Rules.isNatural(playerTotal) && playerTotal > bankerTotal)
                winner = "player";
            else
                winner = "banker";
        }
        else
        {
            if (Rules.isPlayerHit(playerTotal))
            {
                Player.myHand.add( hit() );
                playerTotal = Rules.totalHand(Player.myHand);

                if ( Rules.bankerCalc( Player.myHand.get(2).value%10, bankerTotal) )
                {
                    Banker.myHand.add( hit() );
                    bankerTotal = Rules.totalHand(Banker.myHand);
                }
                winner = (playerTotal > bankerTotal) ?
                        "player" : "banker";

                if (playerTotal.intValue() == bankerTotal.intValue())
                    winner = "tie";
            }
            else
            {
                if (bankerTotal < 6)
                {
                    Banker.myHand.add( hit() );
                    bankerTotal = Rules.totalHand(Banker.myHand);
                }
                winner = (playerTotal > bankerTotal) ?
                        "player" : "banker";

                if (playerTotal.intValue() == bankerTotal.intValue())
                    winner = "tie";
            }
        }

        if ( userSelection.equals(winner) )
        {
            switch (userSelection)
            {
                case "player":
                    settleBet( true, 1.0 );
                    break;
                case "banker":
                    settleBet( true, 0.95 );
                    break;
                case "tie":
                    settleBet( true, 8.0 );
            }
        }
        else
            settleBet(false, 1.0);

        checkShoeCount();

        return winner;
    }

    private static void deal()
    {
        Player.myHand.add( hit() );
        Banker.myHand.add( hit() );
        Player.myHand.add( hit() );
        Banker.myHand.add( hit() );
    }

    private static Card hit()
    {
        Card nextCard = myTable.myShoe.theShoe.lastElement();
        myTable.myShoe.theShoe.remove( myTable.myShoe.theShoe.size()-1 );
        return nextCard;
    }

    public static void settleBet(Boolean didUserWin, Double multiplier)
    {
        if (didUserWin)
        {
            User.setWallet( userBet*multiplier);
            myTable.setTableTotal( -(userBet*multiplier) );
        }
        else
            User.setWallet( -userBet );
    }

    public static void checkBetBounds()
    {
        if (userBet > myTable.tableMax)
            userBet = myTable.tableMax;
        if (userBet > User.wallet.doubleValue())
            userBet = User.wallet.doubleValue();
    }

    public static void checkShoeCount()
    {
        if( myTable.myShoe.theShoe.size() < 6)
            myTable.newShoe();
    }

    public static Boolean isTableMax()
    {
        return ( myTable.tableTotal.intValue() < 1 );
    }

    public static void clear()
    {
        Player.myHand = new Vector<>();
        Banker.myHand = new Vector<>();
    }

    public static void reset()
    {
        myHouse = new House("a");
        User.init();
    }
}
