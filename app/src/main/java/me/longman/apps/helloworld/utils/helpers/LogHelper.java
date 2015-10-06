/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/


package me.longman.apps.helloworld.utils.helpers;

import android.os.Bundle;
import android.util.Log;

import me.longman.apps.helloworld.BuildConfig;
import me.longman.apps.helloworld.Constants;


public class LogHelper {
    public static final String LOG_PREFIX = "ITDC-";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static final boolean PRINT_FILE_LINE = true;
    private static final boolean SHOW_THREAD_NAME = true;
    private static final int MAX_LOG_LENGTH = 1000;


    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }


    public static void LOGD(final String tag, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.DEBUG)) {
            Log.d(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGD(final String tag, Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.DEBUG)) {
            Log.d(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4), cause);
        }
    }

    public static void LOGD(Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.DEBUG)) {
            Log.d(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGD(Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.DEBUG)) {
            Log.d(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4), cause);
        }
    }


    public static void LOGI(final String tag, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.INFO)) {
            Log.i(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGI(final String tag, Object message, Throwable cause) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.INFO)) {
            Log.i(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4), cause);
        }
    }

    public static void LOGI(Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.INFO)) {
            Log.i(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGI(Object message, Throwable cause) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.INFO)) {
            Log.i(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4), cause);
        }
    }



    public static void LOGE(final String tag, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.ERROR)) {
           if (String.valueOf(message).length() > MAX_LOG_LENGTH) {
               logSplit(tag, String.valueOf(message), Log.ERROR);
           } else {
               Log.e(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4));
           }
        }
    }

    public static void LOGE(final String tag, Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.ERROR)) {
            Log.e(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4), cause);
        }
    }

    public static void LOGE(Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.ERROR)) {
            Log.e(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGE(Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.ERROR)) {
            Log.e(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4), cause);
        }
    }




    public static void LOGW(final String tag, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.WARN)) {
            Log.w(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGW(final String tag, Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.WARN)) {
            Log.w(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4), cause);
        }
    }

    public static void LOGW(Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.WARN)) {
            Log.w(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGW(Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.WARN)) {
            Log.w(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4), cause);
        }
    }




    public static void LOGV(final String tag, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.VERBOSE)) {
            Log.v(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGV(final String tag, Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+tag, Log.VERBOSE)) {
            Log.v(LOG_PREFIX+tag, String.valueOf(message)+ getFooter(4), cause);
        }
    }

    public static void LOGV(Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.VERBOSE)) {
            Log.v(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4));
        }
    }

    public static void LOGV(Throwable cause, Object message) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.VERBOSE)) {
            Log.v(LOG_PREFIX+"Log", String.valueOf(message)+ getFooter(4), cause);
        }
    }






    public static void LOGBUNDLE(Bundle args) {
        if (BuildConfig.DEBUG || Constants.DEBUG_MODE || Log.isLoggable(LOG_PREFIX+"Log", Log.DEBUG)) {
            LOGD("=== BUNDLE ===");
            for (String key : args.keySet()) {
                LOGD("\"" + key + "\": " + args.get(key));
            }
        }
    }



    public static String getFooter(int step) {
        String str = "";


	    if (SHOW_THREAD_NAME) {
		    str += " [Thread: "+Thread.currentThread().getName()+"]";
	    }

	    if (PRINT_FILE_LINE) {
            StackTraceElement[] frames = Thread.currentThread().getStackTrace();
            //StackTraceElement frame = frames[2];
            //str = " (File: "+frame.getFileName()+":"+frame.getLineNumber()+")";
            str += " [File: "+frames[step]+"]";
        }
        return str;
    }


    public static void logSplit(String tag, String veryLongString, int type) {
        int length = veryLongString.length();
        int iterations = length / MAX_LOG_LENGTH;
        for(int i = 0; i <= iterations; i++) {
            int start = i * MAX_LOG_LENGTH;
            int end = (i+1) * MAX_LOG_LENGTH;
            end = end > length ? length : end;
            String body = veryLongString.substring(start, end);

            // log
            switch (type) {
                case Log.ERROR:
                    if (i == 0) {
                        Log.e(LOG_PREFIX+tag, "=== Start of dumping large text ===");
                    }
                    Log.e(LOG_PREFIX+tag, body);

                    if (i == iterations) {
                        Log.e(LOG_PREFIX+tag, "=== End of dumping large text ===" + getFooter(5));
                    }
                    break;
            }

        }
    }




    private LogHelper() {
    }
}
