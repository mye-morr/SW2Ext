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
class FcCat4Control extends BaseControl {

    String fc_title;
    String fc1_caption;
    String fc1;
    String fc2_caption;
    String fc2;
    String fc3_caption;
    String fc3;
    String fc4_caption;
    String fc4;

    public FcCat4Control(Context context, String hostAppPackageName, Intent intent) {
        super(context, hostAppPackageName, intent);
    }

    @Override
    public void onDestroy() {
        Log.d(HelloLayoutsExtensionService.LOG_TAG, "onDestroy: FcCat4Control");
    };

    private void updateFc4(String fc_title,
                              String fc1_caption,
                              String fc1,
                              String fc2_caption,
                              String fc2,
                              String fc3_caption,
                              String fc3,
                              String fc4_caption,
                              String fc4,
                              boolean showAnswers) {

        // Prepare a bundle to update the button text.
        Bundle bundle0 = new Bundle();
        bundle0.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt_title);
        bundle0.putString(Control.Intents.EXTRA_TEXT, fc_title);

        Bundle bundle1_caption = new Bundle();
        bundle1_caption.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt1caption);
        bundle1_caption.putString(Control.Intents.EXTRA_TEXT, fc1_caption);

        Bundle bundle1 = new Bundle();
        bundle1.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt1);
        bundle1.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc1 : "");

        Bundle bundle2_caption = new Bundle();
        bundle2_caption.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt2caption);
        bundle2_caption.putString(Control.Intents.EXTRA_TEXT, fc2_caption);

        Bundle bundle2 = new Bundle();
        bundle2.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt2);
        bundle2.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc2 : "");

        Bundle bundle3_caption = new Bundle();
        bundle3_caption.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt3caption);
        bundle3_caption.putString(Control.Intents.EXTRA_TEXT, fc3_caption);

        Bundle bundle3 = new Bundle();
        bundle3.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt3);
        bundle3.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc3 : "");

        Bundle bundle4_caption = new Bundle();
        bundle4_caption.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt4caption);
        bundle4_caption.putString(Control.Intents.EXTRA_TEXT, fc4_caption);

        Bundle bundle4 = new Bundle();
        bundle4.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.txt4);
        bundle4.putString(Control.Intents.EXTRA_TEXT, showAnswers ? fc4 : "");

        Bundle[] bundleData = new Bundle[9];
        bundleData[0] = bundle0;
        bundleData[1] = bundle1_caption;
        bundleData[2] = bundle1;
        bundleData[3] = bundle2_caption;
        bundleData[4] = bundle2;
        bundleData[5] = bundle3_caption;
        bundleData[6] = bundle3;
        bundleData[7] = bundle4_caption;
        bundleData[8] = bundle4;

        showLayout(R.layout.fc_cat_4, bundleData);
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

            fc_title = "  " + fcParts[0];
            fc1_caption = fcParts[1];
            fc1 = " " + fcParts[2];
            fc2_caption = fcParts[3];
            fc2 = " " + fcParts[4];
            fc3_caption = fcParts[5];
            fc3 = " " + fcParts[6];
            fc4_caption = fcParts[7];
            fc4 = " " + fcParts[8];

            startVibrator(150, 200, 2);

            updateFc4(
                    fc_title,
                    fc1_caption,
                    fc1,
                    fc2_caption,
                    fc2,
                    fc3_caption,
                    fc3,
                    fc4_caption,
                    fc4,
                    showAnswers);
        }
    }
}