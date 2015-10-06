/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.events.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import me.longman.apps.helloworld.App;
import me.longman.apps.helloworld.utils.helpers.UIHelper;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public class AppStartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LOGI("Cycle", "AppStartReceiver::onReceive");

        App.instance().postInitApplication();

        UIHelper.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                App.instance().getLocation().startService();
            }
        });
    }
}
