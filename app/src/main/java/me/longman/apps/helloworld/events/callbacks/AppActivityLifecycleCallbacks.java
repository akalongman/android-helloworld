/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.events.callbacks;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import me.longman.apps.helloworld.App;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public final class AppActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public void onActivityCreated(Activity activity, Bundle bundle) {
        //ge.itdc.apps.selfies.App.log("Activity \"" + activity.getClass().getName() + "\" was set");
        LOGI("Cycle", "Activity onActivityCreated: \"" + activity.getLocalClassName() + "\"");
    }

    public void onActivityDestroyed(Activity activity) {
        LOGI("Cycle", "Activity onActivityDestroyed: \"" + activity.getLocalClassName() + "\"");
    }

    public void onActivityPaused(Activity activity) {
        LOGI("Cycle", "Activity onActivityPaused: \"" + activity.getLocalClassName() + "\"");
        App.instance().setAppInForeground(false);
    }

    public void onActivityResumed(Activity activity) {
        App.instance().setActivity(activity);
        LOGI("Cycle", "Activity onActivityResumed: \"" + activity.getLocalClassName() + "\"");
        App.instance().setAppInForeground(true);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LOGI("Cycle", "Activity onActivitySaveInstanceState: \"" + activity.getLocalClassName() + "\"");
    }

    public void onActivityStarted(Activity activity) {
        LOGI("Cycle", "Activity onActivityStarted: \"" + activity.getLocalClassName() + "\"");
    }

    public void onActivityStopped(Activity activity) {
        LOGI("Cycle", "Activity onActivityStopped: \"" + activity.getLocalClassName() + "\"");
    }


}
