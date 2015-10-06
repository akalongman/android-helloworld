/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.Fragment;

import me.longman.apps.helloworld.events.callbacks.AppActivityLifecycleCallbacks;
import me.longman.apps.helloworld.utils.libraries.Location;
import me.longman.apps.helloworld.utils.libraries.PlayServices;
import me.longman.apps.helloworld.utils.libraries.Preferences;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public class App extends Application {
    private volatile Context applicationContext = null;
    private volatile Handler applicationHandler = null;
    private volatile Resources applicationResources = null;
    private volatile Activity currentActivity = null;
    private volatile Fragment currentFragment = null;
    private volatile boolean applicationInited = false;


	//private GCM gcm;
    private Preferences prefs;
    private PlayServices playServices;
    private Location location;

    private boolean appInForeground = false;

    private static App sInstance;

    @Override
	public void onCreate()
	{
		super.onCreate();
		applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());
        applicationResources = getResources();
        registerActivityLifecycleCallbacks(new AppActivityLifecycleCallbacks());
        sInstance = this;
    }

    public void postInitApplication() {
        if (applicationInited) {
            return;
        }
        LOGI("Cycle", "App initialized");

        applicationInited = true;

        //gcm = new GCM(applicationContext);
        prefs = new Preferences(applicationContext);
        playServices = new PlayServices(applicationContext);
        location = new Location(applicationContext);

    }

    public static synchronized App instance() {
        return sInstance;
    }

    public Context getContext() {
        return applicationContext;
    }

    public Fragment getFragment() {
        return currentFragment;
    }

    public Resources getRes() {
        return applicationResources;
    }

    public void setActivity(Activity activity) {
        currentActivity = activity;
    }

    public void setAppInForeground(boolean state) {
        appInForeground = state;
    }

    public void setFragment(Fragment fragment) {
        currentFragment = fragment;
    }

	public Activity getActivity() {
		return currentActivity;
	}

	public Handler getHandler() {
		return applicationHandler;
	}



    public Location getLocation() {
        return location;
    }
}