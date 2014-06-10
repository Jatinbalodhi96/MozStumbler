package org.mozilla.mozstumbler.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Registered as a receiver in manifest. Starts the StumblerService in passive listening mode.
 * Using GPS_* event changes during development, switch to using the existing permissions for a
 * service on Fennec.
 <receiver android:name=".service.PassiveServiceStarter">
 <intent-filter>
 <action android:name="android.location.GPS_ENABLED_CHANGE" />
 <action android:name="android.location.GPS_FIX_CHANGE" />
 </intent-filter>
 </receiver>
 */
public class PassiveServiceStarter extends BroadcastReceiver {
    final static String LOGTAG = PassiveServiceStarter.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //Toast.makeText(context, ":" + action, 1000).show();

        if (!action.contains("GPS_ENABLED_CHANGE") ||
            !action.contains("GPS_FIX_CHANGE")) {
            Log.e(LOGTAG, "Unhandled notification. Please fix.");
            return;
        }

        Intent startServiceIntent = new Intent(context, StumblerService.class);
        startServiceIntent.putExtra(StumblerService.ACTION_START_PASSIVE, true);
        context.startService(intent);
    }
}

