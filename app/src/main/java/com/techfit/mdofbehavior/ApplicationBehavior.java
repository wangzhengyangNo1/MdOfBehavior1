package com.techfit.mdofbehavior;

import android.app.Application;

/**
 * Created by techfit on 2017/2/8.
 */

public class ApplicationBehavior extends Application {

    private static ApplicationBehavior mApp;

    public static ApplicationBehavior getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
