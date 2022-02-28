package com.example.mybaccaratapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mybaccaratapp.src.model.HiScore;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HiScoreActivity extends AppCompatActivity {

    public TextView txt1;
    public TextView txt2;
    public TextView txt3;
    public TextView txt4;
    public TextView txt5;
    public TextView txt6;
    public TextView txt7;
    public TextView txt8;
    public TextView txt9;
    public TextView txt10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_score);

        txt1 = findViewById(R.id.number1);
        txt2 = findViewById(R.id.number2);
        txt3 = findViewById(R.id.number3);
        txt4 = findViewById(R.id.number4);
        txt5 = findViewById(R.id.number5);
        txt6 = findViewById(R.id.number6);
        txt7 = findViewById(R.id.number7);
        txt8 = findViewById(R.id.number8);
        txt9 = findViewById(R.id.number9);
        txt10 = findViewById(R.id.number10);

        init();
    }

    public void init()
    {
        NumberFormat formatter = new DecimalFormat("$##,###,##0.00");

        txt1.setText("\t" + formatter.format(HiScore.topTen.get(0)));
        txt2.setText("\t" + formatter.format(HiScore.topTen.get(1)));
        txt3.setText("\t" + formatter.format(HiScore.topTen.get(2)));
        txt4.setText("\t" + formatter.format(HiScore.topTen.get(3)));
        txt5.setText("\t" + formatter.format(HiScore.topTen.get(4)));
        txt6.setText("\t" + formatter.format(HiScore.topTen.get(5)));
        txt7.setText("\t" + formatter.format(HiScore.topTen.get(6)));
        txt8.setText("\t" + formatter.format(HiScore.topTen.get(7)));
        txt9.setText("\t" + formatter.format(HiScore.topTen.get(8)));
        txt10.setText("\t" + formatter.format(HiScore.topTen.get(9)));
    }
}
