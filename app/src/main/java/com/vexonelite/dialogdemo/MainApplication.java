package com.vexonelite.dialogdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MainApplication extends Application {

    @Override
	public void onCreate() {
		super.onCreate();

        // only for the development to track if the event of memory leak happens
        LeakCanary.install(this);
	}
}
