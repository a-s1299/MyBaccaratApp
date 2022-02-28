package com.example.mybaccaratapp.src.model;

import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;

public class Deck
{
    private String allSuites[] = {"♠", "♥", "♣", "♦"};
    private String allFaces[] = {"A\t","2\t","3\t","4\t","5\t","6\t",
            "7\t","8\t","9\t","⑩","J\t","Q\t","K\t"};
    public Vector<Card> theDeck = new Vector<>();

    public Deck()
    {
        this.buildDeck();
        this.shuffle();
    }

    private void buildDeck()
    {
        Integer valueCounter = 1;

        for ( String suite : allSuites )
            for ( String face : allFaces )
            {
                theDeck.add( new Card(valueCounter++, face, suite) );
                if (valueCounter > 13)
                    valueCounter = 1;
            }
    }

    private void shuffle()
    {
        long seed = new Date().getTime();
        double rand = Math.floor((Math.random() * seed) + 1);
        Collections.shuffle(theDeck, new Random( (long)rand ));
    }
}
