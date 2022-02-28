package com.example.mybaccaratapp.src.model;

import java.math.BigDecimal;

public class House {

    private static final String ZERO = "0.00";
    private static final String FIVE_THOUSAND = "5000";
    private static final String FIVE_MIL = "5000000";
    private static final String TEN_MIL = "10000000";


    public BigDecimal houseTotal;
    public BigDecimal userDebt;

    public House(String type)
    {
        switch(type)//moar to come
        {
            case "a":
                houseTotal = new BigDecimal(TEN_MIL);
                userDebt = new BigDecimal(ZERO);
                break;
        }
    }

    public void setHouseTotal(double totalHouseAdjust)
    {
        houseTotal = new BigDecimal( String.valueOf( houseTotal.doubleValue() + totalHouseAdjust ));
    }

    public void setUserDebt(double totalDebtAdjust)
    {
        userDebt = new BigDecimal( String.valueOf( userDebt.doubleValue() + totalDebtAdjust ));
    }

    public void zeroDebt()
    {
        userDebt = new BigDecimal(ZERO);
    }
}
