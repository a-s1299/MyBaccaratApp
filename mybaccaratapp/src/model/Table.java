package com.example.mybaccaratapp.src.model;

import java.math.BigDecimal;

import com.example.mybaccaratapp.src.controller.Dealer;

public class Table {

    public Shoe myShoe;
    public BigDecimal tableTotal;
    public Double tableMax;
    public Double tableMin;
    public Integer bet0;
    public Integer bet1;
    public Integer bet2;
    public Integer bet3;
    public Integer stakesLevel;


    public Table(int newStakesLevel)
    {
        myShoe = new Shoe();

        switch(newStakesLevel)
        {
            case 1://changed cases up 1 for SingleTable
                tableTotal = new BigDecimal( "5000" );
                tableMax = 1000.00;
                tableMin = 20.00;
                bet0 = tableMin.intValue();
                bet1 = 50;
                bet2 = 100;
                bet3 = 500;
                stakesLevel = newStakesLevel;
                break;

            case 2:
                tableTotal =  new BigDecimal( "20000" );
                tableMax = 5000.00;
                tableMin = 100.00;
                bet0 = tableMin.intValue();
                bet1 = 500;
                bet2 = 1000;
                bet3 = 2000;
                stakesLevel = newStakesLevel;
                break;

            case 3:
                tableTotal = new BigDecimal( "500000" );
                tableMax = 100000.00;
                tableMin = 1000.00;
                bet0 = tableMin.intValue();
                bet1 = 2000;
                bet2 = 5000;
                bet3 = 10000;
                stakesLevel = newStakesLevel;
                break;

            case 4:
                tableTotal = Dealer.myHouse.houseTotal;
                tableMax = 500000.00;
                tableMin = 5000.00;
                bet0 = tableMin.intValue();
                bet1 = 10000;
                bet2 = 20000;
                bet3 = 50000;
                stakesLevel = newStakesLevel;
        }
    }

    public void setTableTotal(double tableTotalAdjust)
    {
        this.tableTotal = new BigDecimal( this.tableTotal.doubleValue() + tableTotalAdjust );
        Dealer.myHouse.setHouseTotal(tableTotalAdjust);
    }

    public void newShoe()
    {
        myShoe = new Shoe();
    }
}
