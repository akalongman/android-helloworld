/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.utils.helpers;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import me.longman.apps.helloworld.App;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGD;
import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGE;


/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;*/


public class ResourcesHelper {

    public static String getString(int id, String... data) {
        Resources res = App.instance().getRes();
        String mystring = res.getString(id);


        if (data.length > 0) {
            switch(data.length) {
                case 1:
                    mystring = String.format(mystring, data[0]);
                    break;

                default:
                    mystring = String.format(mystring, data);
                    break;


            }
        }


        return mystring;
    }

    /*public static String[] getStringArray(String name) {
        Resources res = ge.itdc.apps.selfies.App.getRes();
        int resid = ResourcesHelper.getResId(name, R.array.class);
        return res.getStringArray(resid);
    }*/

    public static int getColor(int id) {
        Resources res = App.instance().getRes();
        int color = res.getColor(id);
        return color;
    }

    public static int getStyleAttr(int id) {
        Context context = App.instance().getContext();
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(id, outValue, true);
        return outValue.resourceId;
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int dpToPx(int dp) {
        Resources res = App.instance().getRes();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    public static int pxToDp(int px) {
        Resources res = App.instance().getRes();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, res.getDisplayMetrics());
    }

    public static Bitmap getImageThumbnail(Uri uri) {
        InputStream in;
        try {
            Context activity = App.instance().getActivity();
            ContentResolver mContentResolver = activity.getContentResolver();

            final int IMAGE_MAX_SIZE = 120000; // 0.12MP
            in = mContentResolver.openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();


            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            LOGD("scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;
            in = mContentResolver.openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                LOGD("1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            LOGD("bitmap size - width: " + b.getWidth() + ", height: " + b.getHeight());
            return b;
        } catch (IOException e) {
            LOGE(e, "IOException: " + e.getMessage());
            return null;
        }
    }


    public static Bitmap loadPrescaledBitmap(String filename) throws IOException {
        // Facebook image size
        final int IMAGE_MAX_SIZE = 630;

        File file;
        FileInputStream fis;

        BitmapFactory.Options opts;
        int resizeScale;
        Bitmap bmp;

        file = new File(filename);

        // This bit determines only the width/height of the bitmap without loading the contents
        opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        fis = new FileInputStream(file);
        BitmapFactory.decodeStream(fis, null, opts);
        fis.close();

        // Find the correct scale value. It should be a power of 2
        resizeScale = 1;

        if (opts.outHeight > IMAGE_MAX_SIZE || opts.outWidth > IMAGE_MAX_SIZE) {
            resizeScale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(opts.outHeight, opts.outWidth)) / Math.log(0.5)));
        }

        // Load pre-scaled bitmap
        opts = new BitmapFactory.Options();
        opts.inSampleSize = resizeScale;
        fis = new FileInputStream(file);
        bmp = BitmapFactory.decodeStream(fis, null, opts);

        fis.close();

        return bmp;
    }


    public static Bitmap loadBitmap(String filename) throws IOException {
        //File image = new File(filename);

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inScaled = false;
        bmOptions.inDither = false;
        bmOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap = BitmapFactory.decodeFile(filename,bmOptions);

        //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);


        return bitmap;
    }



    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param uri The Uri to query.
     */
    @SuppressWarnings("NewApi")
    public static String getPath(final Uri uri) {
        final Context context = App.instance().getContext();

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static String bytesToSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }


    public static File getCacheDir() {
        if (Environment.getExternalStorageState() == null || Environment.getExternalStorageState().startsWith(Environment.MEDIA_MOUNTED)) {
            try {
                File file = App.instance().getContext().getExternalCacheDir();
                if (file != null) {
                    return file;
                }
            } catch (Exception e) {
                LOGE(e, "getExternalCacheDir");
            }
        }
        try {
            File file = App.instance().getContext().getCacheDir();
            if (file != null) {
                return file;
            }
        } catch (Exception e) {
            LOGE(e, "getCacheDir");
        }
        return new File("");
    }


    /*public static Bitmap getBarCode(String data, int width, int height)
    {
        MultiFormatWriter writer = new MultiFormatWriter();
        String finaldata = Uri.encode(data, "utf-8");
        BitMatrix bm = new BitMatrix(width, height);
        try {
            bm = writer.encode(finaldata, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            LOGE(e, "WriterException");
        }

        int width2 = bm.getWidth();
        int height2 = bm.getHeight();
        Bitmap ImageBitmap = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width2; i++) {//width
            for (int j = 0; j < height2; j++) {//height
                ImageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
            }
        }

        return ImageBitmap;
    }*/

    // Stream conversation to string
    public static String streamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }


}


