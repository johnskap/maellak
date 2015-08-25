package org.gmele.ellak.ellak6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ServiceAct extends Activity implements View.OnClickListener
{
    Button BtStart;
    Button BtStop;
    Button BtStrange;
    MusicService MS;
    EditText et1;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.lay_act);
        BtStart = (Button) findViewById (R.id.BtStart);
        BtStop = (Button) findViewById (R.id.BtStop);
        BtStrange = (Button) findViewById (R.id.BtStrange);
        BtStart.setOnClickListener (this);
        BtStop.setOnClickListener (this);
        BtStrange.setOnClickListener (this);
        et1 =(EditText)findViewById(R.id.editText1);
        et1.setText("-1");

    }



    @Override
    public void onClick (View v)
    {
        if (v == BtStart)
        {
            Intent ServInt = new Intent (this, MusicService.class);
            String i = et1.getText().toString();
            int id = Integer.parseInt(i);

            if (id>=0)
            {
                ServInt.putExtra("trnum", id);
                startService(ServInt);
            }

        }
        if (v == BtStop)
        {
            MS = MusicService.MSRef;
            Intent ServInt = new Intent (this, MusicService.class);
            stopService (ServInt);
        }
        if (v == BtStrange)
            MS.StartSong ();
    }
}
