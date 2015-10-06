/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

import me.longman.apps.helloworld.App;
import me.longman.apps.helloworld.R;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGW;


public class UIHelper {
    public static float density = 1;
    public static Point displaySize = new Point();
    private static Boolean isTablet = null;

    static {
        density = App.instance().getContext().getResources().getDisplayMetrics().density;
        checkDisplaySize();
    }


    public static int dp(float value) {
        return (int)Math.ceil(density * value);
    }



    public static void checkDisplaySize() {
        try {
            WindowManager manager = (WindowManager)App.instance().getContext().getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                Display display = manager.getDefaultDisplay();
                if (display != null) {
                    if(Build.VERSION.SDK_INT < 13) {
                        displaySize.set(display.getWidth(), display.getHeight());
                    } else {
                        display.getSize(displaySize);
                    }
                    LOGW("display size = " + displaySize.x + " " + displaySize.y);
                }
            }
        } catch (Exception e) {
            LOGE(e, "WindowManager");
        }
    }



    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            App.instance().getHandler().post(runnable);
        } else {
            App.instance().getHandler().postDelayed(runnable, delay);
        }
    }

    public static void cancelRunOnUIThread(Runnable runnable) {
        App.instance().getHandler().removeCallbacks(runnable);
    }



    public static boolean isTablet() {
        if (isTablet == null) {
            isTablet = App.instance().getContext().getResources().getBoolean(R.bool.isTablet);
        }
        return isTablet;
    }

    public static boolean isSmallTablet() {
        float minSide = Math.min(displaySize.x, displaySize.y) / density;
        return minSide <= 700;
    }

    public static int getMinTabletSide() {
        if (!isSmallTablet()) {
            int smallSide = Math.min(displaySize.x, displaySize.y);
            int leftSide = smallSide * 35 / 100;
            if (leftSide < dp(320)) {
                leftSide = dp(320);
            }
            return smallSide - leftSide;
        } else {
            int smallSide = Math.min(displaySize.x, displaySize.y);
            int maxSide = Math.max(displaySize.x, displaySize.y);
            int leftSide = maxSide * 35 / 100;
            if (leftSide < dp(320)) {
                leftSide = dp(320);
            }
            return Math.min(smallSide, maxSide - leftSide);
        }
    }



    public static int getCurrentActionBarHeight() {
        if (isTablet()) {
            return dp(64);
        } else if (App.instance().getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return dp(48);
        } else {
            return dp(56);
        }
    }

    public static Point getRealScreenSize() {
        Point size = new Point();
        try {
            WindowManager windowManager = (WindowManager) App.instance().getContext().getSystemService(Context.WINDOW_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                windowManager.getDefaultDisplay().getRealSize(size);
            } else {
                try {
                    Method mGetRawW = Display.class.getMethod("getRawWidth");
                    Method mGetRawH = Display.class.getMethod("getRawHeight");
                    size.set((Integer) mGetRawW.invoke(windowManager.getDefaultDisplay()), (Integer) mGetRawH.invoke(windowManager.getDefaultDisplay()));
                } catch (Exception e) {
                    size.set(windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight());
                    LOGE(e, "getRealScreenSize");
                }
            }
        } catch (Exception e) {
            LOGE(e, "getRealScreenSize");
        }
        return size;
    }


}
