package org.gmele.tei.cnt.cntlab3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartUp extends BroadcastReceiver
{
    public StartUp ()
    {
    }

    @Override
    public void onReceive (Context context, Intent intent)
    {
        Intent DSI = new Intent(context, DnsService.class);
        context.startService (DSI);
    }
}
