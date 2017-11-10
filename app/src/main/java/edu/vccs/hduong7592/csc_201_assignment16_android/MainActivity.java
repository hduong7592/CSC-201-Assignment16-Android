package edu.vccs.hduong7592.csc_201_assignment16_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * Create by hieuduong on 11/9/17
 *
 * CSC 201 - Assignment 16
 * Problem 16.4:
 *
 * (Create a miles/kilometers converter) Write a program that converts miles and
 * kilometers, as shown in Figure 16.37b. If you enter a value in the Mile text field
 * and press the Enter key, the corresponding kilometer measurement is displayed
 * in the Kilometer text field. Likewise, if you enter a value in the Kilometer text
 * field and press the Enter key, the corresponding miles is displayed in the Mile
 * text field.
 */

public class MainActivity extends AppCompatActivity {

    EditText kilometerTxt, mileTxt;
    TextView statusLB;
    boolean mileChange, kiloChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kilometerTxt = (EditText) findViewById(R.id.kmInput);
        mileTxt = (EditText) findViewById(R.id.mileInput);
        statusLB = (TextView) findViewById(R.id.statusLB);


        //Add text listener to the edit text
        //this will trigger every time user type on the textbox
        mileTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0){
                    Convert(mileTxt.getText().toString(),"toKilometer");
                }
                else{
                    statusLB.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Add text listener to the edit text
        //this will trigger every time user type on the textbox

        kilometerTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0){
                    Convert(kilometerTxt.getText().toString(), "toMile");
                }
                else {
                    statusLB.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * Conversion method
     * @param text
     * @param whichConversion
     */

    private void Convert(String text, String whichConversion) {


        double value = 0.0;
        boolean isNumber = false;

        //Check for real input number
        try{
            value = Double.parseDouble(text);
            isNumber = true;
        }
        catch (Exception ex){
            value = 0.0;
            isNumber = false;
            statusLB.setText("Not a real number");
        }

        //If input is real number, perform the conversion

        if(isNumber){

            try {
                double convertedValue = 0.0;
                DecimalFormat df = new DecimalFormat("#.#####");
                df.setRoundingMode(RoundingMode.CEILING);

                if (whichConversion.equals("toMile")) {
                    convertedValue = value * 0.621371;
                    statusLB.setText("="+df.format(convertedValue).toString()+" mile(s).");
                } else {
                    convertedValue = value / 0.621371;
                    statusLB.setText("="+df.format(convertedValue).toString()+" kilometer(s).");
                }
            }
            catch (Exception ex){
                statusLB.setText(ex.toString());
            }
        }

    }
}
