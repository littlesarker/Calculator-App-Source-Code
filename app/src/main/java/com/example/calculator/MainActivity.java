package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private int[] numericButtons = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9};

    private int[] operatorButtons = {R.id.btAdd, R.id.btSubtract, R.id.btMultiply, R.id.btDivide};

    private TextView textScreen;
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;

    Button p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        textScreen=findViewById(R.id.textScreen);

        setNumericOnClickListener();

        setOperatorOnClickListener();

        p=findViewById(R.id.percentID);

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PERCENTAGE.class);
                startActivity(intent);
            }
        });




    }

    private void setNumericOnClickListener() {
        // Create a common OnClickListener
        View.OnClickListener listener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                Button button=(Button) v;
                if(stateError)
                {
                    // If current state is Error, replace the error message
                    textScreen.setText(button.getText());
                    stateError=false;
                }else
                {
                    textScreen.append(button.getText());
                }
                lastNumeric=true;
            }
        };

        for(int id:numericButtons)
        {
            // Assign the listener to all the numeric buttons
            findViewById(id).setOnClickListener(listener);
        }


    }

    private void setOperatorOnClickListener() {


        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the current state is Error do not append the operator
                // If the last input is number only, append the operator
                if(lastNumeric && !stateError)
                {

                    Button button=(Button) v;
                    textScreen.append(button.getText());
                    lastNumeric=false;
                    lastDot=false;
                }

            }
        };

            for(int id: operatorButtons)
            {
                findViewById(id).setOnClickListener(listener);
            }


            findViewById(R.id.btDot).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(lastNumeric && !stateError && !lastDot)
                    {
                        textScreen.append(".");
                        lastNumeric=false;
                        lastDot=true;
                    }

                }
            });


            findViewById(R.id.btClear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    textScreen.setText("");
                    lastNumeric=false;
                    stateError=false;
                    lastDot=false;
                }
            });

            findViewById(R.id.btEqual).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    onEqual();

                }
            });

    }

    private void onEqual() {

        if(lastNumeric && !stateError)
        {
            String txt=textScreen.getText().toString();

            Expression expression=new ExpressionBuilder(txt).build();

            try {
                double result=expression.evaluate();
                textScreen.setText(Double.toString(result));
            }catch (ArithmeticException e)
            {
                textScreen.setText("Error");
                stateError=true;
                lastNumeric=false;

            }

        }


    }

}