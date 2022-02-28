package com.example.mybaccaratapp.src.controller;

import com.example.mybaccaratapp.src.model.Card;
import java.util.Vector;

public class Rules
{
    private static Boolean bankerHitChart[][] = { //based on player's 3rd card
            {true,true,true,true,true,true,true,true,true,true},//0
            {true,true,true,true,true,true,true,true,true,true},//1
            {true,true,true,true,true,true,true,true,true,true},//2
            {true,true,true,true,true,true,true,true,false,true},//3
            {false,false,true,true,true,true,true,true,false,false},//4
            {false,false,false,false,true,true,true,true,false,false},//5
            {false,false,false,false,false,false,true,true,false,false},//6
            {false,false,false,false,false,false,false,false,false,false}//7
    };

    public static Boolean isNatural(Integer total)
    {
        return (total == 8 || total == 9);
    }

    public static Integer totalHand(Vector<Card> theHand)
    {
        Integer total = 0;
        for ( Card card : theHand )
        {
            if (card.value > 10)
                total += 10;
            else
                total += card.value;
        }

        return total % 10;
    }

    public static Boolean isPlayerHit(Integer playerTotal)
    {
        return (playerTotal < 6);
    }

    public static Boolean bankerCalc(Integer playersThirdCard, Integer bankerTotal)
    {
        return bankerHitChart[bankerTotal][playersThirdCard];
    }
}
