package com.example.android.tipscalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double cost = 0;
    double percentage = 0;
    boolean fields = false;
    EditText costBox;
    EditText percentageBox;
    Button button;
    SeekBar seekBar;
    TextView seekBarIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        costBox = (EditText) findViewById(R.id.cost);
        percentageBox = (EditText) findViewById(R.id.percentage);
        button = (Button) findViewById(R.id.calculate);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarIndicator = (TextView) findViewById(R.id.seekBarIndicator);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                progress = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarIndicator.setText((progress+1) + "/" + (seekBar.getMax()+1));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                seekBarIndicator.setText((progress+1) + "/" + (seekBar.getMax()+1));

            }
        });

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
        if((seekBar.getProgress()+1) > 1)
        displaySplitAmount(runSplitCalculation(cost,percentage));

    }


    public double runCalculation(double cost, double percentage) {
        return cost * (percentage * 0.01);

    }

    public double runSplitCalculation(double cost, double percentage) {
        return (runCalculation(cost, percentage)+cost)/(seekBar.getProgress()+1);

    }

    public void displayAmount(double number) {
        String formattedResult = String.format("%.02f", number);
        TextView resultAmount = (TextView) findViewById(R.id.result);
        resultAmount.setText("Your tip amount is: $" + formattedResult);

    }

    public void displaySplitAmount(double number){
        String formattedResult = String.format("%.02f", number);
        TextView resultAmount = (TextView) findViewById(R.id.splitResult);
        resultAmount.setText("Total amount for each person is: $" + formattedResult);

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