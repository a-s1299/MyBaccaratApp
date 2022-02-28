package com.example.mybaccaratapp.src.model;

import com.example.mybaccaratapp.src.controller.Dealer;

import java.util.Collections;
import java.util.Vector;


public class HiScore {

    public static Vector<Integer> topTen;

    public static void hiScoreInit()
    {
        topTen = new Vector<>(10);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
        topTen.add(0);
    }

    public static void addScore(Double toAdd)
    {
        if ( (topTen.lastElement() < toAdd) )
        {
            topTen.add(9, toAdd.intValue());
            orderScores();
        }
    }

    public static Double calcScore()
    {
        return ( User.wallet.doubleValue() - Dealer.myHouse.userDebt.doubleValue() );
    }

    public static void orderScores()
    {
        Collections.sort(topTen, Collections.<Integer>reverseOrder());
    }

    public static void clearScores()
    {
        hiScoreInit();
    }
}
