package com.pan.info;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public class InfoApplication extends Application {
    private static final boolean DEBUG = true;

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRefWatcher = LeakCanary.install(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        InfoApplication application = (InfoApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }
}
