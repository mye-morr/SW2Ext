/*
Copyright (c) 2011, Sony Ericsson Mobile Communications AB
Copyright (c) 2011-2013, Sony Mobile Communications AB

 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 * Neither the name of the Sony Ericsson Mobile Communications AB / Sony Mobile
 Communications AB nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.example.sonymobile.smartextension.habitaidsw2ext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.sonyericsson.extras.liveware.aef.control.Control;

/**
 * This demonstrates two different approaches, bitmap and layout, for displaying
 * a UI. The bitmap approach is useful for accessories without layout support,
 * e.g. SmartWatch.
 * This sample shows all UI components that can be used, except Gallery and
 * ListView.
 */
class FcLang3Control extends BaseControl {

    String fc_top;
    String fc_middle;
    String fc_bottom;

    public FcLang3Control(Context context, String hostAppPackageName, Intent intent) {
        super(context, hostAppPackageName, intent);
    }

    @Override
    public void onDestroy() {
        Log.d(HelloLayoutsExtensionService.LOG_TAG, "onDestroy: FcCat4Control");
    };

    private void updateFc(String fc_top,
                              String fc_middle,
                              String fc_bottom,
                              boolean showAnswers) {

        // Prepare a bundle to update the button text.
        Bundle bundle0 = new Bundle();
        bundle0.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.tv_top);
        bundle0.putString(Control.Intents.EXTRA_TEXT, fc_top);

        Bundle bundle1 = new Bundle();
        bundle1.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.tv_middle);
        bundle1.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc_middle : "");

        Bundle bundle2 = new Bundle();
        bundle2.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.tv_bottom);
        bundle2.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc_bottom : "");

        Bundle[] bundleData = new Bundle[3];
        bundleData[0] = bundle0;
        bundleData[1] = bundle1;
        bundleData[2] = bundle2;

        showLayout(R.layout.fc_lang_3, bundleData);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean showAnswers = true;

        if (prefs.getBoolean("fc", false)) { // new SMS notification
            prefs.edit().putBoolean("fc", false).commit(); // reset the flag

            String[] fcParts = prefs.getString("string_fc", "").split("\\|");
            showAnswers = prefs.getBoolean("bool_showAnswers", true);

            fc_top = "  " + fcParts[0];
            fc_middle = fcParts[1];
            fc_bottom = " " + fcParts[2];

            startVibrator(150, 200, 2);

            updateFc(
                    fc_top,
                    fc_middle,
                    fc_bottom,
                    showAnswers);
        }
    }
}