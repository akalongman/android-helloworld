/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;


public class FileHelper {


    public static File getMediaStorageDir() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + PackageHelper.getName()
                + "/files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        return mediaStorageDir;
    }

    /** Create a File for saving an image or video */
    public static File saveMediaFile(Bitmap bitmap, String file_name){
        File mediaStorageDir = getMediaStorageDir();
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + file_name);
        try {
            FileOutputStream fos = new FileOutputStream(mediaFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            LOGE(e, "File not found: " + e.getMessage());
        }
        catch (IOException e) {
            LOGE(e, "IOException: " + e.getMessage());
        }
        return mediaFile;
    }

}
