package com.example.john.lab1;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity
{
    Button bt1;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(buttonListener);






    }


    View.OnClickListener buttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            bt1.setText("koko");

        }

    };




}
