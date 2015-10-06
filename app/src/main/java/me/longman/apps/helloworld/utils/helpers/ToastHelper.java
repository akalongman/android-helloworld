/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;

import android.content.Context;
import android.widget.Toast;

import me.longman.apps.helloworld.App;


public class ToastHelper {



    public static void show(String message, boolean... longLength) {
        Context context = App.instance().getContext();
        boolean isDurationLong = false;
        if (longLength.length > 0) {
            isDurationLong = longLength[0];
        }
        quickToast(context, message, isDurationLong);
    }

    public static void show(int msg, boolean... longLength) {
        Context context = App.instance().getContext();
        String message = ResourcesHelper.getString(msg);
        boolean isDurationLong = false;
        if (longLength.length > 0) {
            isDurationLong = longLength[0];
        }
        quickToast(context, message, isDurationLong);
    }




    /**
     * Display a toast with the given message (Length will be Toast.LENGTH_SHORT -- approx 2 sec).
     *
     * @param context The current Context or Activity that this method is called from
     * @param message Message to display
     * @return Toast object that is being displayed. Note,show() has already been called on this object.
     */
    public static Toast quickToast(Context context, String message) {
        return quickToast(context, message, false);
    }

    /**
     * Display a toast with the given message.
     *
     * @param context    The current Context or Activity that this method is called from
     * @param message    Message to display
     * @param longLength if true, will use Toast.LENGTH_LONG (approx 3.5 sec) instead of
     * @return Toast object that is being displayed. Note,show() has already been called on this object.
     */
    public static Toast quickToast(Context context, String message, boolean longLength) {
        final Toast toast;
        if (longLength) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.show();
        return toast;
    }
}
