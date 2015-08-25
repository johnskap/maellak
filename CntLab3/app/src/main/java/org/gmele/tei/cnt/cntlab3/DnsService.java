package org.gmele.tei.cnt.cntlab3;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class DnsService extends Service
{
    static boolean ILive = false;
    String GetStr;
    Timer MyTimer;

    TimerTask MyTask = new TimerTask ()
    {
        @Override
        public void run ()
        {
            if (isConnected ())
            {
                ShowMessage (GetPage (GetStr));
            }
            else
            {
                ShowMessage ("Out of Network");
            }
        }
    };

    public DnsService ()
    {

    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        ILive = true;
        Toast toast = Toast.makeText (this, "Glad to Serve...", Toast.LENGTH_LONG);
        toast.show ();
        LoadUrl ();
        MyTimer = new Timer ();
        MyTimer.schedule (MyTask, 2000, 60000);
        return START_STICKY;
    }

    public void onDestroy ()
    {
        ILive = false;
        MyTimer.cancel ();
        MyTimer.purge ();
        Toast toast = Toast.makeText (this, "No one needs me any more...", Toast.LENGTH_LONG);
        toast.show ();
    }

    private boolean isConnected ()
    {
        ConnectivityManager Mgr = (ConnectivityManager) getSystemService (this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Mgr.getActiveNetworkInfo ();
        if (networkInfo != null && networkInfo.isConnected ())
            return true;
        else
            return false;
    }

    private String GetPage (String addr)
    {
        int Count = 0;
        try
        {
            HttpClient httpClient = new DefaultHttpClient ();
            HttpGet httpGet = new HttpGet (addr);
            HttpResponse response = httpClient.execute (httpGet);
            BufferedReader reader = new BufferedReader (new InputStreamReader (response.getEntity ().getContent ()));
            String Res = reader.readLine ();
            return Res;
        }
        catch (IOException e)
        {
            return "Get Error: " + e.getMessage ();
        }
    }

    private void LoadUrl ()
    {
        String FilePath = getApplicationContext ().getFilesDir () + "/" + "Link.txt";
        File LinkFile = new File (FilePath);
        int Byte;
        if (!LinkFile.exists ())
        {
            try
            {
                InputStream in = getAssets ().open ("Link.txt");
                FileOutputStream out = new FileOutputStream (LinkFile);
                while ((Byte = in.read ()) != -1)
                    out.write (Byte);
                in.close ();
                out.close ();
            }
            catch (IOException e)
            {
                ShowMessage ("Cannot Copy Link from Assets");
            }
        }
        try
        {
            InputStream fis = new FileInputStream (FilePath);
            InputStreamReader isr = new InputStreamReader (fis, Charset.forName ("UTF-8"));
            BufferedReader br = new BufferedReader (isr);
            GetStr = br.readLine ();
        }
        catch (FileNotFoundException e)
        {
            ShowMessage ("Link.txt not found. Should never happen.");
            GetStr = "http://freedns.afraid.org/dynamic/update.php?dE56YzRFTGJrWGlXbElvazlRZ2U6MTQ1MDk1MDk=";
        }
        catch (IOException e)
        {
            ShowMessage ("Could not read \"Link.txt\". Should never happen");
            GetStr = "http://freedns.afraid.org/dynamic/update.php?dE56YzRFTGJrWGlXbElvazlRZ2U6MTQ1MDk1MDk=";
        }

    }

    public void ShowMessage (String Mess)
    {
        Message msg = new Message ();
        Bundle b = new Bundle ();
        b.putString ("Message", Mess);
        msg.setData (b);
        MyHandler.sendMessage (msg);
    }

    Handler MyHandler = new Handler ()
    {
        @Override
        public void handleMessage (Message Msg)
        {
            Bundle bu = Msg.getData ();
            String Mess = bu.getString ("Message");
            Toast MyToast = Toast.makeText (getApplicationContext (), Mess, Toast.LENGTH_LONG);
            MyToast.setGravity (Gravity.CENTER, 0, 0);
            MyToast.show ();
        }
    };

    //Binding methods and data
    /*
    NEXT FRIDAY

    private final IBinder mBinder = new LocalBinder ();


    public class LocalBinder extends Binder
    {
        DnsService getService ()
        {
            return DnsService.this;
        }
    }
    */

    @Override
    public IBinder onBind (Intent intent)
    {
        return null;
    }

}
