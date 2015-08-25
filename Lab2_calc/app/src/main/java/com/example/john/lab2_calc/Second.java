package com.example.john.lab2_calc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Second extends Activity implements View.OnClickListener
{

    Button btplus, btminus;
    int num1, num2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btplus = (Button)findViewById(R.id.button3);
        btplus.setOnClickListener(this);

        btminus = (Button)findViewById(R.id.button4);
        btminus.setOnClickListener(this);

        tv3 = (TextView)findViewById(R.id.textView3);

        Intent myIntent2 = getIntent();
        Bundle bu = myIntent2.getExtras();
        String tmp1 = bu.getString("number1");
        String tmp2 = bu.getString("number2");
        num1 = Integer.parseInt(tmp1);
        num2 = Integer.parseInt(tmp2);

    }



    @Override
    public void onClick(View v)
    {
        if (v == btplus)
        {
            prakseis(1, num1, num2);
        }

        if (v == btminus)
        {
            prakseis(2, num1, num2);
        }

    }



    public void prakseis(int opp, int num1, int num2)
    {
        int sum=0;
        if (opp==1)
        {
            sum=num1+num2;
        }
        else if(opp==2)
        {
            sum=num1-num2;
        }


        Intent MyIntent3 = new Intent(this, Calculations.class);
        MyIntent3.putExtra("sum", String.valueOf(sum));
        startActivityForResult (MyIntent3, 13);

    }



    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 13)
            if (resultCode == RESULT_OK)
            {
                String Res = data.getExtras().getString ("Result");
                tv3.setText (Res);
            }
    }



    }





