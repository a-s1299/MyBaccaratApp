package com.example.mybaccaratapp.src.model;

import com.example.mybaccaratapp.src.controller.Dealer;

import java.math.BigDecimal;

public class User
{
    public static BigDecimal wallet;
    public static Integer status;

    public static void init()
    {
        wallet = new BigDecimal("500.00");
        status = 0;
    }

    public static void dev_mode()
    {
        wallet = new BigDecimal("50000.00");
        status = 3;
        Dealer.myHouse.houseTotal = new BigDecimal( "5000.00" );
    }

    public static void setWallet(double outcome)
    {
        wallet = new BigDecimal( String.valueOf( wallet.doubleValue() + outcome ));
    }
}
