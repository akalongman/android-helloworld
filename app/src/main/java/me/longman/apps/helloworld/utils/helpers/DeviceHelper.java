/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Base64;
import android.util.Patterns;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import me.longman.apps.helloworld.App;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGD;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;


public class DeviceHelper {
    private static String mail;


    public static String getEMail() {
        if (mail == null) {
            Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
            String possibleEmail = "";
            AccountManager accountManager = AccountManager.get(App.instance().getContext());
            Account[] accounts;

            try {
                accounts = accountManager.getAccounts();
                for (Account account : accounts) {
                    // ge.itdc.apps.selfies.App.log("Account: "+account);
                    if (emailPattern.matcher(account.name).matches()) {
                        possibleEmail = account.name;
                        break;
                    }
                }
            }
            catch(NullPointerException e) {
                LOGE(e, "Error while getting account information");
            }

            if (possibleEmail.length() > 0) {
                return possibleEmail;
            }
            mail = "";
        }

        return mail;
    }


    public static String getDeviceId() {
        return Settings.Secure.getString(App.instance().getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            LOGD("Package Name=" + context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                LOGD("Key Hash=" + key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            LOGE(e1, "Name not found");
        }

        catch (NoSuchAlgorithmException e) {
            LOGE(e, "No such an algorithm");
        } catch (Exception e) {
            LOGE(e, "Exception");
        }

        return key;
    }

    /**
     * Checks to see if the user has rotation enabled/disabled in their phone settings.
     *
     * @param context The current Context or Activity that this method is called from
     * @return true if rotation is enabled, otherwise false.
     */
    public static boolean isRotationEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
    }

    /**
     * Checks to see if the device is connected to a network (cell, wifi, etc).
     *
     * @param context The current Context or Activity that this method is called from
     * @return true if a network connection is available, otherwise false.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Check if there is any connectivity to a Wifi network.
     * <p/>
     * Can be used in combination with {@link #isConnectedMobile}
     * to provide different features if the device is on a wifi network or a cell network.
     *
     * @param context The current Context or Activity that this method is called from
     * @return true if a wifi connection is available, otherwise false.
     */
    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     * <p/>
     * Can be used in combination with {@link #isConnectedWifi}
     * to provide different features if the device is on a wifi network or a cell network.
     *
     * @param context The current Context or Activity that this method is called from
     * @return true if a mobile connection is available, otherwise false.
     */
    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }



}
