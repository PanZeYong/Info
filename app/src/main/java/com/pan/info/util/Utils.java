package com.pan.info.util;

import android.content.Context;

import com.pan.info.Constant;

import java.io.File;

/**
 * Author : Pan
 * Date : 17/03/2017
 */

public class Utils {
    public static File createCacheDir(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), Constant.CACHE_DIR_NAME);
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    public static int calculateMemorySize() {
        return Constant.CACHE_SIZE;
    }
}
