/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.longman.apps.helloworld.App;
import me.longman.apps.helloworld.R;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


public class BaseActivity extends AppCompatActivity {


    @Bind(R.id.toolbar) protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Post initialize application
        App.instance().postInitApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    protected void initToolbar() {
        ButterKnife.bind(this);

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    public void enableBackArrow() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        catch(NullPointerException e) {
            LOGE("setDisplayHomeAsUpEnabled/setDisplayShowHomeEnabled error");
        }
    }

    public void disableBackArrow() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        catch(NullPointerException e) {
            LOGE("setDisplayHomeAsUpEnabled/setDisplayShowHomeEnabled error");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String extras = "{}";
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                StringBuffer buf = new StringBuffer("{");
                for (String key : bundle.keySet()) {
                    buf.append(" " + key + " => " + bundle.get(key) + ";");
                    buf.append(" }");
                }
                extras = buf.toString();
            }
        }


        LOGI("Cycle", "onActivityResult: \"" + getLocalClassName() + "\". requestCode: "+requestCode+". resultCode: "+resultCode+". data: "+data+", extras: "+extras);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LOGI("Cycle", "onNewIntent: \"" + getLocalClassName() + "\". intent: " + intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        String extras = "{}";
        if (bundle != null) {
            StringBuffer buf = new StringBuffer("{");
            for (String key : bundle.keySet()) {
                buf.append(" " + key + " => " + bundle.get(key) + ";");
                buf.append(" }");
            }
            extras = buf.toString();
        }


        LOGI("Cycle", "onSaveInstanceState: \"" + getLocalClassName() + "\". bundle: "+extras+"");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }







}
