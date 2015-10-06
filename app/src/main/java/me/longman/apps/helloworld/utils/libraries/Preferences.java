/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.libraries;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGD;


public class Preferences {
    private final Context context;
    private final SharedPreferences prefs;

    public static final String GCM_REG_ID = "registration_id";
    public static final String GCM_APP_VERSION = "app_version";




    public Preferences(Context context) {
        this.context = context;
        //ge.itdc.apps.selfies.App.log("getPackageName: "+context.getPackageName());
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    public String getString(String key, String defval) {
        return prefs.getString(key, defval);
    }


    public boolean getBoolean(String key, boolean defval) {
        return prefs.getBoolean(key, defval);
    }

    public int getInt(String key, int defval) {
        return prefs.getInt(key, defval);
    }

    public void putString(String key, String val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, val);
        editor.apply();
    }

    public void putInt(String key, int val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, val);
        editor.apply();
    }


    public Map<String,?> getAll() {
        return this.prefs.getAll();
    }



    public void dumpAll() {
        Map<String,?> keys = getAll();
        LOGD("== Prefs DUMP ==");
        for(Map.Entry<String,?> entry : keys.entrySet()){
            LOGD(entry.getKey() + ": " +
                    entry.getValue().toString());
        }
    }





}
