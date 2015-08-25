package org.gmele.ellak.ellak6;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener
{
    final String[] Tracks = {"track1.mp3", "track2"};
    MediaPlayer MP;
    int CurTrack;
    static MusicService MSRef;

    public MusicService ()
    {
        MSRef = this;
    }

    @Override
    public IBinder onBind (Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException ("Not yet implemented");
    }


    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {

        Bundle bu;
        bu = intent.getExtras();
        String Temp = bu.getString("trnum");
        int i = Integer.parseInt(Temp);
        CurTrack = i;

        Toast toast = Toast.makeText (this, "Selected Truck : " + CurTrack, Toast.LENGTH_LONG);
        toast.show ();
        MP = new MediaPlayer ();
        MP.setOnCompletionListener (this);
        StartSong ();
        return START_STICKY;
    }

    public void onDestroy ()
    {
        MP.stop ();
        Toast toast = Toast.makeText (this, "No one needs me any more...", Toast.LENGTH_LONG);
        toast.show ();
    }

    @Override
    public void onCompletion (MediaPlayer mediaPlayer)
    {
        if (++CurTrack == Tracks.length)
            CurTrack = 0;
        StartSong ();
    }

    public void StartSong ()
    {
        try
        {
            MP.reset ();
            AssetFileDescriptor afd = getAssets ().openFd (Tracks[CurTrack]);
            MP.setDataSource (afd.getFileDescriptor (), afd.getStartOffset (), afd.getLength ());
            afd.close ();
            MP.setAudioStreamType (AudioManager.STREAM_MUSIC);
            MP.prepare ();
            MP.start ();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println ("Error #1" + e.getMessage ());
        }
        catch (IllegalStateException e)
        {
            System.out.println ("Error #2" + e.getMessage ());
        }
        catch (IOException e)
        {
            System.out.println ("Error #3 " + e.getMessage ());
        }
    }


}
