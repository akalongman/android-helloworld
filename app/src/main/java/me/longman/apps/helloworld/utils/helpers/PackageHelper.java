/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import me.longman.apps.helloworld.App;
import me.longman.apps.helloworld.R;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;


public class PackageHelper {

    private static String app_version;
    private static int app_version_code = 0;
    private static String app_name;

    public static String getAppVersion() {
        if (app_version == null) {
            Context context = App.instance().getContext();
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo pInfo = packageManager.getPackageInfo(packageName, 0);
                app_version = pInfo.versionName;
            }
            catch(PackageManager.NameNotFoundException e) {
                LOGE(e, "Package Name not found");
            }
            catch(NullPointerException e) {
                LOGE(e, "Package Name not found");
            }
        }
        return app_version;
    }



    public static int getAppVersionCode() {
        if (app_version_code == 0) {
            Context context = App.instance().getContext();
            try {
                PackageInfo packageInfo = context.getPackageManager()
                        .getPackageInfo(context.getPackageName(), 0);
                return packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                // should never happen
                throw new RuntimeException("Could not get package name: " + e);
            }
        }
        return app_version_code;
    }

    public static String getAppName() {
        if (app_name == null) {
            app_name = ResourcesHelper.getString(R.string.app_name);
        }
        return app_name;
    }


    /**
     * Get application name from Manifest file.
     *
     * @param context The current Context or Activity that this method is called from
     * @return application name.
     */
    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }


    public static String getName() {
        Context context = App.instance().getContext();
        return context.getPackageName();
    }


    public static String getKeyHash() {
        PackageInfo packageInfo;
        String key = null;
        Context context = App.instance().getContext();
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            //Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                //Log.e("Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }



}
