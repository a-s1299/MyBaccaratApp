package com.example.mybaccaratapp.src.model;

public class Card
{
    public Integer value;
    public String face;
    public String suite;

    public Card(Integer newValue, String newFace, String newSuite)
    {
        value = newValue;
        face = newFace;
        suite = newSuite;
    }
}
