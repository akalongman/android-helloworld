/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {
    private static FragmentTransaction fragmentTransaction;



    public static void beginTransaction(Activity activity) {
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    public static void replace(int containerViewId, Fragment fragment, String tag, String backStack) {
        fragmentTransaction.replace(containerViewId, fragment, tag);
        if (!backStack.equals("")) {
            fragmentTransaction.addToBackStack(backStack);
        }
    }



    public static void commit() {
        //fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
    }



}
