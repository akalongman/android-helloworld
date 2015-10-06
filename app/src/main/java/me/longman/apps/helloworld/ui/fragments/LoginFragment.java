/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.longman.apps.helloworld.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends BaseFragment {

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
