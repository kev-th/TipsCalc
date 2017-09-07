package com.example.android.tipscalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double cost = 0;
    double percentage = 0;
    boolean fields = false;
    EditText costBox;
    EditText percentageBox;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        costBox = (EditText) findViewById(R.id.cost);
        percentageBox = (EditText) findViewById(R.id.percentage);
        button = (Button) findViewById(R.id.calculate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fields = validate(new EditText[]{costBox, percentageBox});
                if (fields) {
                    callCalculation();
                }
            }
        });

    }


    public void callCalculation() {


        cost = Double.parseDouble(costBox.getText().toString());


        percentage = Double.parseDouble(percentageBox.getText().toString());


        // fields = validate(new EditText[]{costBox,percentageBox});

        displayAmount(runCalculation(cost, percentage));
    }


    public double runCalculation(double cost, double percentage) {
        return cost * (percentage * 0.01);


    }

    public void displayAmount(double number) {
        String formattedResult = String.format("%.02f", number);
        TextView resultAmount = (TextView) findViewById(R.id.result);
        resultAmount.setText("Your amount is: " + formattedResult);
    }

    private boolean validate(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }
}