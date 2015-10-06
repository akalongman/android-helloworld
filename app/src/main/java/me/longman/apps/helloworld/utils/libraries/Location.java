/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.libraries;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

public class Location {

    private LocationManager locationManager;
    private Boolean locationServiceBoolean = false;
    private int providerType = 0;
    private static AlertDialog alert;

    public Location(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

    }



    public Boolean isLocationServiceAvailable() {
        boolean gpsIsEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkIsEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (networkIsEnabled && gpsIsEnabled) {
            locationServiceBoolean = true;
            providerType = 1;

        } else if (!networkIsEnabled && gpsIsEnabled) {
            locationServiceBoolean = true;
            providerType = 2;

        } else if (networkIsEnabled && !gpsIsEnabled) {
            locationServiceBoolean = true;
            providerType = 1;
        }

        return locationServiceBoolean;
    }

    public int getProviderType() {
        return providerType;
    }

    public void showLocationServiceError(final Activity activityObj) {

        // show alert dialog if Internet is not connected
        AlertDialog.Builder builder = new AlertDialog.Builder(activityObj);

        builder.setMessage(
                "You need to enable location service to use this application. " +
                        "Please turn on GPS mode in location settings")
                .setTitle("Warning")
                .setCancelable(false)
                .setPositiveButton("Enable...",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                activityObj.startActivity(intent);
                                alert.dismiss();
                            }
                        })
                .setNegativeButton("Not now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                alert.dismiss();
                                activityObj.finish();
                            }
                        });
        alert = builder.create();
        alert.show();
    }

    public void hideLocationServiceError(final Activity activityObj) {
        if (alert != null) {
            alert.dismiss();
        }

    }

    public void startService() {
        /*if (App.getUser().isEmpty()) {
            return;
        }

        // start location service
        AlarmManager alarmManager = (AlarmManager) App.getContext().getSystemService(Context.ALARM_SERVICE);

        Intent runLocationServiceIntent = new Intent(App.getContext(), LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(App.getContext(), 0, runLocationServiceIntent, 0);

        LOGD("START SERVICE: " + pendingIntent.hashCode());

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                Config.SERVICE_LOCATION_INTERVAL,
                Config.SERVICE_LOCATION_INTERVAL, pendingIntent);

*/
    }

    public void stopService() {

        /*// stop service
        AlarmManager alarmManager = (AlarmManager) App.getContext().getSystemService(Context.ALARM_SERVICE);

        Intent runLocationServiceIntent = new Intent(App.getContext(), LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(App.getContext(), 0, runLocationServiceIntent, 0);

        alarmManager.cancel(pendingIntent);

        App.getContext().stopService(runLocationServiceIntent);


        LOGD("STOP SERVICE: " + pendingIntent.hashCode());

*/
    }

}
