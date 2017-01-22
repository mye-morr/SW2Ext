package com.example.sonymobile.smartextension.habitaidsw2ext;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;

public class BaseControl extends ControlExtension {
    private static final String LOG_TAG;
    private Intent mIntent;

    static {
        LOG_TAG = BaseControl.class.getName();
    }

    public BaseControl(Context context, String hostAppPackageName, Intent intent) {
        super(context, hostAppPackageName);
        this.mIntent = intent;
    }

    public void onError(int code) {
        Log.e(LOG_TAG, "Error Code: " + code);
    }

    public void onMenu() {
    }
}
