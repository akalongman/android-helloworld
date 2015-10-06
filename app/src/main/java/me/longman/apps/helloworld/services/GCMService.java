/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.services;

import android.app.IntentService;
import android.content.Intent;

import me.longman.apps.helloworld.events.receivers.GCMBroadcastReceiver;


/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class GCMService extends IntentService {

    public GCMService() {
        super(GCMService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //LOGI("GCMService: onHandleIntent");

        //Notifications notifications = new Notifications(this, intent);

        //notifications.initialize();

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

}
