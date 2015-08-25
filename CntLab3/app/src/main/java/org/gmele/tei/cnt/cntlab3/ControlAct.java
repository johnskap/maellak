package org.gmele.tei.cnt.cntlab3;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ControlAct extends Activity implements Button.OnClickListener
{

    TextView TvStatus;
    Button BtChange;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.controllay);
        TvStatus = (TextView) findViewById (R.id.TvStatus);
        TvStatus.setOnClickListener (this);
        BtChange = (Button) findViewById (R.id.BtChangeStatus);
        BtChange.setOnClickListener (this);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void onResume ()
    {
        super.onResume ();
        ShowStatus ();
    }

    @Override
    public void onBackPressed ()
    {
        finish ();
    }

    @Override
    public void onClick (View v)
    {
        if (v == TvStatus)
            ShowStatus ();
        if (v == BtChange)
        {
            DoChange ();
            /*
            try
            {
                Thread.sleep (1000);
            }
            catch (InterruptedException e)
            {

            }
            */
            ShowStatus ();
        }
    }

    void ShowStatus ()
    {
        if (IsServiceActive ())
        {
            TvStatus.setText ("Η Υπηρεσία είναι ενεργή");
            TvStatus.setTextColor (Color.GREEN);
        }
        else
        {
            TvStatus.setText ("Η Υπηρεσία δεν είναι ενεργή");
            TvStatus.setTextColor (Color.rgb (255, 0, 0));
        }
    }

    boolean IsServiceActive ()
    {
        return DnsService.ILive;
    }

    boolean IsServiceActiveHardWay ()
    {
        ActivityManager manager = (ActivityManager) getSystemService (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices (Integer.MAX_VALUE))
        {
            if ("org.gmele.tei.cnt.cntlab3.DnsService".equals (service.service.getClassName ()))
            {
                return true;
            }
        }
        return false;
    }

    void DoChange ()
    {
        if (IsServiceActiveHardWay ())
        {
            Toast.makeText (this, "Stopping Service...", Toast.LENGTH_LONG).show ();
            Intent tss = new Intent (this, DnsService.class);
            stopService (tss);
        }
        else
        {
            Toast.makeText (this, "Starting Service...", Toast.LENGTH_LONG).show ();
            Intent tss = new Intent (this, DnsService.class);
            startService (tss);
        }
    }

}
