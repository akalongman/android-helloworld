/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.longman.apps.helloworld.App;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGI("Cycle", "Fragment onCreate: \"" + ((Object) this).getClass().getName() + "\"");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LOGI("Cycle", "Fragment onCreateView: \"" + ((Object) this).getClass().getName() + "\" savedInstanceState \"" + savedInstanceState + "\"");
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        App.instance().setFragment(this);
        LOGI("Cycle", "Fragment onResume: \"" + ((Object) this).getClass().getName() + "\"");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LOGI("Cycle", "Fragment onActivityResult: \"" + ((Object) this).getClass().getName() + "\"");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGI("Cycle", "Fragment onPause: \"" + ((Object) this).getClass().getName() + "\"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGI("Cycle", "Fragment onDestroy: \"" + ((Object) this).getClass().getName() + "\"");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOGI("Cycle", "Fragment onDestroyView: \"" + ((Object) this).getClass().getName() + "\"");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LOGI("Cycle", "Fragment onSaveInstanceState: \"" + ((Object) this).getClass().getName() + "\"");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        LOGI("Cycle", "Fragment onDetach: \"" + ((Object) this).getClass().getName() + "\"");
    }



}