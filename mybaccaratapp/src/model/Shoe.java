package com.example.mybaccaratapp.src.model;

import java.util.Vector;

public class Shoe
{
    private Deck tempDeck;
    public Vector<Card> theShoe = new Vector<>();

    public Shoe()
    {
        tempDeck = new Deck();
        fillShoe();
    }

    private void fillShoe()
    {
        for(int i = 0; i < 8 ; ++i)
        {
            for(Card theCard : tempDeck.theDeck)
                theShoe.add( theCard );

            tempDeck = new Deck();
        }
    }
}
