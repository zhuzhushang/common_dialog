package com.noahedu.yxpdialog.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.telecom.ConnectionService;

public class NetworkUtils {

    /**
     * @return 是否有网络
     */
    public static boolean isConnected(Context context){

        boolean isConnect = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        isConnect = networkInfo != null ? networkInfo.isConnected() : false;

        return isConnect;
    }

}
