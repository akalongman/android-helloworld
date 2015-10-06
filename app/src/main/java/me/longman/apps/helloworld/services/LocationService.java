/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;

import me.longman.apps.helloworld.App;
import me.longman.apps.helloworld.events.listeners.LocationListener;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGD;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public class LocationService extends Service
{
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 60000; // milliseconds
    private static final float LOCATION_DISTANCE = 5f; //10f


    android.location.LocationListener[] mLocationListeners = new android.location.LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
	    return START_STICKY;
    }

    @Override
    public void onCreate()
    {

	    // initialize application
        App.instance().postInitApplication();

        initializeLocationManager();

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (SecurityException ex) {
            LOGE("Location", ex, "Fail to request Network provider location update, ignore");
        } catch (IllegalArgumentException ex) {
            LOGE("Location", ex, "Network provider does not exist, ");
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (SecurityException ex) {
            LOGE("Location", ex, "Fail to request GPS provider location update, ignore");
        } catch (IllegalArgumentException ex) {
            LOGE("Location", ex, "GPS provider does not exist ");
        }



    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    LOGD("Location", ex, "Fail to remove location listeners, ignore");
                }
            }
        }
    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) App.instance().getContext().getSystemService(Context.LOCATION_SERVICE);
            LOGI("Cycle", "LocationManager initialized: "+hashCode());
        }
    }




}