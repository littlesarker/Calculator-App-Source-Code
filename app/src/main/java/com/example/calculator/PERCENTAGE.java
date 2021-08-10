package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PERCENTAGE extends AppCompatActivity {

    EditText amm,pp;
    Button res;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage);

        amm=findViewById(R.id.amID);
        pp=findViewById(R.id.editText);
        res=findViewById(R.id.resultd);
        textView=findViewById(R.id.textID);




        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(amm.getText().toString()) || TextUtils.isEmpty(pp.getText().toString()))
                {
                    Toast.makeText(PERCENTAGE.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    int amount=Integer.parseInt(amm.getText().toString());
                    int per=Integer.parseInt(pp.getText().toString());

                    double re=amount/100;

                    double rees=re*per;

                    String tmpStr10 = String.valueOf(rees);
                    textView.setText(tmpStr10);


                    amm.getText().clear();
                    pp.getText().clear();

                }

            }
        });


    }

}