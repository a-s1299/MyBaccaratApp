package com.example.mybaccaratapp.src.view;

import com.example.mybaccaratapp.src.model.*;

import java.util.Vector;

public class Display
{
    public static String getHand(Vector<Card> theHand)
    {
        String handString = new String();
//        for (Card card : theHand)
//        {
//            handString += card.face + " of " + card.suite + "'s\n";
//        }
        for (Card card : theHand)
        {
            handString += "┌──┐\n" +
                    "│  " + card.face+ "  │\n" +
                    "│ " + card.suite +"  │\n" +
                    "└──┘\n";
        }
        return handString;
    }

    public static String formatBets(Integer toFormat)
    {
        String formatted;

        if (toFormat < 1000)
            formatted = "$"+toFormat.toString();
        else
            formatted = "$"+(toFormat/1000)+"k";

        return formatted;
    }
}
