/*
* @author 		Avtandil Kikabidze aka LONGMAN
* @copyright 	Copyright (c) 2008-2015, Avtandil Kikabidze aka LONGMAN (akalongman@gmail.com)
* @link 		http://longman.me
* @license 		MIT license;
*/

package me.longman.apps.helloworld.events.listeners;

import android.location.Location;
import android.os.Bundle;

import static me.longman.apps.helloworld.utils.helpers.LogHelper.LOGI;


/*
import ge.itdc.apps.selfies.enums.UsersItemType;
import ge.itdc.apps.selfies.network.requests.UsersRequest;
import ge.itdc.apps.selfies.ui.activities.BaseActivity;
import ge.itdc.apps.selfies.ui.fragments.UserMapFragment;
*/

public class LocationListener implements android.location.LocationListener {
    Location mLastLocation;
    //UsersRequest request;
    public LocationListener(String provider)
    {
        LOGI("Location", "LocationListener " + provider);
        mLastLocation = new Location(provider);
        //request = new UsersRequest(UsersItemType.UPDATE, null);

    }

    @Override
    public void onLocationChanged(Location location) {
        LOGI("Location", "onLocationChanged: " + location);
        mLastLocation.set(location);



        /*
        // if user not authorized, disable location sending on server
        if (!App.getUser().isAuthenticated()) {
            LOGW("Location", "onLocationChanged: user not authenticated! user: " + App.getUser().toJSON());
            return;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        float accuracy = location.getAccuracy();
        double altitude = location.getAltitude();
        float bearing = location.getBearing();
        float speed = location.getSpeed();

        String address = "";


        Geocoder gcd = new Geocoder(ge.itdc.apps.selfies.App.getContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                int getMaxAddressLineIndex = addresses.get(0).getMaxAddressLineIndex();
                for (int i = 0; i<=getMaxAddressLineIndex; i++) {
                    if (i > 0) {
                        address = address + ", ";
                    }
                    address = address + addresses.get(0).getAddressLine(i);
                }
            }
        }
        catch (IOException e) {
            LOGD(e, "Geocoder error");
        }

        String strLatitude = String.valueOf(latitude);
        String strLongitude = String.valueOf(longitude);
        String strAccuracy = String.valueOf(accuracy);
        String strAltitude = String.valueOf(altitude);
        String strAddress = String.valueOf(address);
        String strBearing = String.valueOf(bearing);
        String strSpeed = String.valueOf(speed);

        // send on server
        params.put("latitude", strLatitude);
        params.put("longitude", strLongitude);
        params.put("accuracy", strAccuracy);
        params.put("altitude", strAltitude);
        params.put("location", strAddress);
        params.put("bearing", strBearing);
        params.put("speed", strSpeed);
        //request.update(params);

        // update in User object
        //App.getUser().setLocation(location);

        Fragment fragment = App.getFragment();
        if (fragment == null) {
            LOGW("Fragment is NULL!");
        } else {
            BaseActivity activity = (BaseActivity) fragment.getActivity();
            if (activity == null) {
                LOGE("Activity is NULL!");
            } else {
                // check distance with partner
                activity.checkDistance();
                if (fragment.getTag() != null) {
                    if (fragment.getTag().equals("user_map")) {
                        //LOGI("Location", "Updated self location on the map");
                        ((UserMapFragment) fragment).updateSelfLocation();
                    }
                }

            }
        }*/
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        LOGI("Location", "onProviderDisabled: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        LOGI("Location", "onProviderEnabled: " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        LOGI("Location", "onStatusChanged: " + provider);
    }
}
