package com.pan.info.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pan.info.Constant;

/**
 * Author : Pan
 * Date : 15/03/2017
 */

public class NetworkUtils {

    /**
     * 判断当前网络是否连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取当前网络类型
     * @param context
     * @return
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo && networkInfo.isConnected()) {
                if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
                    return Constant.NETWORK_MOBILE;
                } else if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                    return Constant.NETWORK_WIFI;
                }
            }
        }
        return Constant.NETWORK_NONE;
    }
}
