/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Map;

import me.longman.apps.helloworld.App;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;


public class ActivityHelper {

    public static void start(Activity from, Class<?> to) {
        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
        //from.finish();
    }

    public static void replace(Activity from, Class<?> to) {
        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
        from.finish();
    }




    public static void start(Activity from, Class<?> to, Bundle args) {
        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.putExtras(args);
	    from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
        //from.finish();
    }

    public static void replace(Class<?> to) {
        Activity from = App.instance().getActivity();
        //ge.itdc.apps.selfies.App.log("ACTIVITY: "+from);


        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
        from.finish();
    }


    public static void start(Class<?> to) {
        Activity from = App.instance().getActivity();
        //ge.itdc.apps.selfies.App.log("ACTIVITY: "+from);

        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
    }

    public static void start(Class<?> to, Bundle args) {
        Activity from = App.instance().getActivity();


        //ge.itdc.apps.selfies.App.log("ACTIVITY: "+from);

        Intent intent = new Intent(App.instance().getContext(), to);
        from.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(args);
        from.getWindow().setWindowAnimations(0);
        from.startActivity(intent);
    }






    public static void showDialog(String title, String body, String type) {
        AlertDialog alertDialog = new AlertDialog.Builder(App.instance().getActivity()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(body);
        alertDialog.setCancelable(true);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Add your code for the button here.

            }
        });

        // Set the Icon for the Dialog
        if (type.equals("info")) {
            alertDialog.setIcon(android.R.drawable.ic_dialog_info);
        } else if (type.equals("error")) {
            alertDialog.setIcon(android.R.drawable.stat_notify_error);
        }


        alertDialog.show();
    }





    /**
     * Force screen to turn on if the phone is asleep.
     *
     * @param context The current Context or Activity that this method is called from
     */
    public static void turnScreenOn(Activity context) {
        try {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        } catch (Exception ex) {
            LOGE(ex, "Unable to turn on screen for activity");
        }
    }

    /**
     * Launch an Activity.
     *
     * @param context              The current Context or Activity that this method is called from.
     * @param activity             The new Activity to open.
     * @param closeCurrentActivity whether or not the current activity should close.
     * @param params               Parameters to add to the intent as a Bundle.
     */
    public static void launchActivity(Activity context, Class<? extends Activity> activity, boolean closeCurrentActivity, Map<String, String> params) {
        Intent intent = new Intent(context, activity);

        if (params != null) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> param : params.entrySet()) {
                bundle.putString(param.getKey(), param.getValue());
            }
            intent.putExtras(bundle);
        }

        context.startActivity(intent);
        if (closeCurrentActivity) {
            context.finish();
        }
    }
}
