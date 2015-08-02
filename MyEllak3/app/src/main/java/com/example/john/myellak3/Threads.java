package com.example.john.myellak3;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Threads extends ActionBarActivity implements OnClickListener
{
    final String[] Sites = {"http://expertphone.gr/?p=1440", "http://www.skroutz.gr/products/show/19234525", "http://www.google.gr"};
    TextView TvMess;
    Button BtDoit;
    Button BtNext;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.lay_main1);
        TvMess = (TextView) findViewById (R.id.TvMessage);
        BtDoit = (Button) findViewById (R.id.BtDoit);
        BtDoit.setOnClickListener (this);
        BtNext = (Button) findViewById (R.id.BtNext);
        BtNext.setOnClickListener (this);
    }

    @Override
    public void onClick (View v)
    {
        if (v == BtDoit)
        {
            int Page = new Random ().nextInt (3);
            MyThread Mt = new MyThread (this, Sites[Page]);
            Mt.start ();
        }
        if (v == BtNext)
        {
            Intent intent = new Intent (getApplicationContext (), Timers.class);
            startActivity (intent);
        }
    }

    public void ShowMessage (String Mess)
    {
        Message msg = new Message ();
        Bundle bun = new Bundle ();
        bun.putString ("Message", Mess);
        msg.setData (bun);
        MyHandler.sendMessage (msg);
    }

    Handler MyHandler = new Handler ()
    {
        @Override
        public void handleMessage (Message Mess)
        {
            Bundle b = Mess.getData ();
            String tbp = b.getString ("Message");
            TvMess.setText (tbp);
        }
    };
}

class MyThread extends Thread
{
    Threads Father;
    String Site;

    MyThread (Threads f, String s)
    {
        Father = f;
        Site = s;
    }

    @Override
    public void run ()
    {
        int count = 0;
        Father.ShowMessage ("Getting: " + Site);
        HttpClient httpclient = new DefaultHttpClient ();
        HttpGet httpget = new HttpGet (Site);
        HttpResponse response;
        try
        {
            response = httpclient.execute (httpget);
            InputStream Inp = response.getEntity ().getContent ();
            BufferedReader Reader = new BufferedReader (new InputStreamReader (Inp));
            while (Reader.read () != -1)
                count++;
            Reader.close ();
            Father.ShowMessage (Site + " size: " + count);
        }
        catch (Exception e) //ClientProtocolException | IOException
        {
            System.err.println (e.getMessage ());
        }

    }
}
