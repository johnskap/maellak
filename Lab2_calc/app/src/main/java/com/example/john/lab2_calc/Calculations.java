package com.example.john.lab2_calc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Calculations extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);


        Bundle bu;
        bu = getIntent().getExtras();
        String sum=bu.getString("sum");
        String newsum="*"+sum+"*";

        Intent Ret = new Intent();
        Ret.putExtra ("Result", newsum);

        setResult(RESULT_OK, Ret);
        finish ();


    }





}
