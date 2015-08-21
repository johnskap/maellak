package com.example.john.myellak3;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Timers extends Activity implements OnClickListener
{
    int Counter = 0;
    Button BtStart;
    Button BtStop;
    Button BtReset;
    TextView TvShow;
    Timer MyTimer = null;
    TimerTask MyTask = null;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.lay_main2);
        BtStart = (Button) findViewById (R.id.BtStart);
        BtStop = (Button) findViewById (R.id.BtStop);
        BtReset = (Button) findViewById (R.id.BtReset);
        TvShow = (TextView) findViewById (R.id.TvShow);
        BtStart.setOnClickListener (this);
        BtStop.setOnClickListener (this);
        BtReset.setOnClickListener (this);
    }

    @Override
    public void onClick (View v)
    {
        if (v == BtReset)
        {
            Counter = 0;
            TvShow.setText ("0");
        }
        if (v == BtStart)
            DoStart ();
        if (v == BtStop)
            DoStop ();
    }

    public void DoStart ()
    {
        MyTask = new TimerTask ()
        {
            public void run ()
            {
                Counter++;
                ShowMessage (Counter);
            }
        };
        if (MyTimer == null)
            MyTimer = new Timer ();
        MyTimer.schedule (MyTask, 0, 2000);
    }

    public void DoStop ()
    {
        if (MyTimer != null)
        {
            MyTimer.cancel ();
            MyTimer.purge ();
            MyTimer = null;
        }
    }

    public void ShowMessage (int Val)
    {
        Bundle bun = new Bundle ();
        bun.putInt ("Value", Val);
        Message msg = new Message ();
        msg.setData (bun);
        MyHandler.sendMessage (msg);
    }

    Handler MyHandler = new Handler ()
    {
        @Override
        public void handleMessage (Message Mess)
        {
            Bundle b = Mess.getData ();
            int tbp = b.getInt ("Value");
            TvShow.setText (String.valueOf (tbp));
        }
    };

}
