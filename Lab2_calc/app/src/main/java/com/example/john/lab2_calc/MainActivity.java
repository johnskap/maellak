package com.example.john.lab2_calc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity
{
    EditText etnum1, etnum2;
    Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnum1 = (EditText) findViewById(R.id.editText1);
        etnum2 = (EditText) findViewById(R.id.editText2);


        btNext = (Button) findViewById (R.id.button1);
        btNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String num1 = etnum1.getText().toString();
                String num2 = etnum2.getText().toString();

                Intent myintent = new Intent(MainActivity.this, Second.class);
                myintent.putExtra("number1", num1);
                myintent.putExtra("number2", num2);
                startActivity(myintent);
            }
        });

    }






}
