package com.example.john.myellak3;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet (Site);
        HttpResponse response;
        try
        {
            response = httpclient.execute (httpget);
            InputStream Inp = response.getEntity ().getContent ();
            BufferedReader Reader = new BufferedReader (new InputStreamReader(Inp));
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