package com.example.john.lab4ask1;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener
{

    int Counter = 0;
    Button BtFF;
    TextView tv1;
    Timer MyTimer = null;
    TimerTask MyTask = null;
    ProgressBar prog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        BtFF = (Button)findViewById(R.id.button1);
        BtFF.setOnClickListener(this);
        prog1 = (ProgressBar)findViewById(R.id.progressBar1);



        MyOnStart();


    }


    @Override
    public void onClick(View v)
    {
        if (v == BtFF)
        {
            Counter = Counter+10;
        }
    }






    public void MyOnStart ()
    {
        MyTask = new TimerTask ()
        {
            public void run ()
            {
                Counter++;
                ShowMessage (Counter);
                myCheck();

            }
        };
        if (MyTimer == null)
            MyTimer = new Timer ();
        MyTimer.schedule (MyTask, 3000, 1000);
    }


    public void myCheck(){

        if (Counter>=100)
        {
            //MyTask.cancel();
            Counter=0;
            ShowMessage(Counter);
            //Toast toast = Toast.makeText(getApplicationContext(), "TELOS", Toast.LENGTH_LONG);
            //toast.show();
        }

    }



    public void ShowMessage (int Val)
    {
        Bundle bun = new Bundle ();
        bun.putInt("Value", Val);
        Message msg = new Message();
        msg.setData(bun);
        MyHandler.sendMessage (msg);

    }

    Handler MyHandler = new Handler()
    {
        @Override
        public void handleMessage (Message Mess)
        {
            Bundle b = Mess.getData ();
            int tbp = b.getInt ("Value");
            tv1.setText (String.valueOf (tbp));
            prog1.setProgress(tbp);

            if(tbp>=100){
                Toast toast = Toast.makeText(getApplicationContext(), "TELOS", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    };



}
