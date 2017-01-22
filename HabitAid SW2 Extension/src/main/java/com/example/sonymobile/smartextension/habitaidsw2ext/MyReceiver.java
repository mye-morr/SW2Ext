package com.example.sonymobile.smartextension.habitaidsw2ext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyReceiver extends BroadcastReceiver {

    private static final String HOST_APP_PACKAGE_NAME = "com.sonymobile.smartconnect.smartwatch2";

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("com.example.SendBroadcast")) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("notif", true)
                    .putString("string_notif", intent.getStringExtra("STRING_MSG"))
                    .commit();

            if(intent.getStringExtra("STRING_MSG").equalsIgnoreCase("status")) {
                Intent startIntent = new Intent(context, ButtonsControl.class);
                ControlManager.getInstance(context).startControl(startIntent);
            }
            else {
                Intent startIntent = new Intent(context, NotifControl.class);
                ControlManager.getInstance(context).startControl(startIntent);
            }
        }

        else if(intent.getAction().equals("com.example.SendBroadcast.fc1")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("fc", true)
                    .putString("string_fc", intent.getStringExtra("STRING_FC"))
                    .putBoolean("bool_showAnswers", intent.getBooleanExtra("BOOL_SHOW_ANSWERS", true))
                    .commit();

            Intent startIntent = new Intent(context, Fc1Control.class);
            ControlManager.getInstance(context).startControl(startIntent);
        }
        else if(intent.getAction().equals("com.example.SendBroadcast.fc2")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("fc", true)
                    .putString("string_fc", intent.getStringExtra("STRING_FC"))
                    .putBoolean("bool_showAnswers", intent.getBooleanExtra("BOOL_SHOW_ANSWERS", true))
                    .commit();

            Intent startIntent = new Intent(context, Fc2Control.class);
            ControlManager.getInstance(context).startControl(startIntent);
        }

        else if(intent.getAction().equals("com.example.SendBroadcast.fc3")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("fc", true)
                    .putString("string_fc", intent.getStringExtra("STRING_FC"))
                    .putBoolean("bool_showAnswers", intent.getBooleanExtra("BOOL_SHOW_ANSWERS", true))
                    .commit();

            Intent startIntent = new Intent(context, Fc3Control.class);
            ControlManager.getInstance(context).startControl(startIntent);
        }

        else if(intent.getAction().equals("com.example.SendBroadcast.fc_lang_3")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("fc", true)
                    .putString("string_fc", intent.getStringExtra("STRING_FC"))
                    .putBoolean("bool_showAnswers", intent.getBooleanExtra("BOOL_SHOW_ANSWERS", true))
                    .commit();

            Intent startIntent = new Intent(context, FcLang3Control.class);
            ControlManager.getInstance(context).startControl(startIntent);
        }

        else if(intent.getAction().equals("com.example.SendBroadcast.fc_cat_4")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit()
                    .putBoolean("fc", true)
                    .putString("string_fc", intent.getStringExtra("STRING_FC"))
                    .putBoolean("bool_showAnswers", intent.getBooleanExtra("BOOL_SHOW_ANSWERS", true))
                    .commit();

            Intent startIntent = new Intent(context, FcCat4Control.class);
            ControlManager.getInstance(context).startControl(startIntent);
        }
    }
}

        /*
        String message = "Broadcast intent detected "
                + intent.getAction();

        Toast.makeText(context, message,
                Toast.LENGTH_LONG).show();
        */

        /*
        Intent i1 = new Intent(Control.Intents.CONTROL_START_REQUEST_INTENT);
        i1.putExtra(Widget.Intents.EXTRA_AEA_PACKAGE_NAME, context.getPackageName());
        i1.setPackage(intent.getStringExtra(Control.Intents.EXTRA_AHA_PACKAGE_NAME));
        context.sendBroadcast(i1, Registration.HOSTAPP_PERMISSION);
        */

        /*
        Intent i2 = new Intent(Control.Intents.CONTROL_SEND_TEXT_INTENT);
        i2.putExtra(Widget.Intents.EXTRA_AEA_PACKAGE_NAME, context.getPackageName());
        i2.setPackage(intent.getStringExtra(Control.Intents.EXTRA_AHA_PACKAGE_NAME));
        i2.putExtra(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_1_3);
        i2.putExtra(Control.Intents.EXTRA_TEXT, "\n\n" + intent.getStringExtra("STRING_MSG"));
        context.sendBroadcast(i2, Registration.HOSTAPP_PERMISSION);

        Intent i3 = new Intent(Control.Intents.CONTROL_SEND_TEXT_INTENT);
        i3.putExtra(Widget.Intents.EXTRA_AEA_PACKAGE_NAME, context.getPackageName());
        i3.setPackage(intent.getStringExtra(Control.Intents.EXTRA_AHA_PACKAGE_NAME));
        i3.putExtra(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt_title);
        i3.putExtra(Control.Intents.EXTRA_TEXT, intent.getStringExtra("STRING_MSG"));
        context.sendBroadcast(i3, Registration.HOSTAPP_PERMISSION);
        */

