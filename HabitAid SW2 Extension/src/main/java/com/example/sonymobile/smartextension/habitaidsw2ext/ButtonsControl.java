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
import com.sonyericsson.extras.liveware.extension.util.control.ControlObjectClickEvent;

/**
 * This demonstrates two different approaches, bitmap and layout, for displaying
 * a UI. The bitmap approach is useful for accessories without layout support,
 * e.g. SmartWatch.
 * This sample shows all UI components that can be used, except Gallery and
 * ListView.
 */
class ButtonsControl extends BaseControl {

    /*
    enum RenderType {
        LAYOUT, BITMAP
    }

    private RenderType mRenderType = RenderType.LAYOUT;
    */

    private String sCaptions = "";
    private String sPoints = "";

    // !!! add this later as a:
    // subcat1 | caption (pts)
    String[] sxSubCat = new String[14];

    String[] sxCaptions = new String[14];
    String[] sxPoints = new String[14];

    String sCategory = "";
    private int mActiveFace = 2;

    // front / top / bottom / reVerse / left / right

    public ButtonsControl(Context context, String hostAppPackageName, Intent intent) {
        super(context, hostAppPackageName, intent);
    }

    @Override
    public void onDestroy() {
        Log.d(HelloLayoutsExtensionService.LOG_TAG, "onDestroy: ButtonsControl");
    };

    @Override
    public void onObjectClick(final ControlObjectClickEvent event) {
        Log.d(HelloLayoutsExtensionService.LOG_TAG,
                "onObjectClick: ButtonsControl click type: " + event.getClickType());

        String sPressedCaption = "";
        String sPressedPoints = "";

        // Check which view was clicked and then take the desi4red action.
        switch (event.getLayoutReference()) {
            case R.id.btn_1_1: // show/hide
                sPressedCaption = sxCaptions[12];
                sPressedPoints = sxPoints[12];
                break;
            case R.id.btn_1_2: // reset
                sPressedCaption = sxCaptions[13];
                sPressedPoints = sxPoints[13];
                break;
            case R.id.btn_1_3:
                break;
            case R.id.btn_2_1:
                sPressedCaption = sxCaptions[0];
                sPressedPoints = sxPoints[0];
                break;
            case R.id.btn_2_2:
                sPressedCaption = sxCaptions[1];
                sPressedPoints = sxPoints[1];
                break;
            case R.id.btn_2_3:
                sPressedCaption = sxCaptions[2];
                sPressedPoints = sxPoints[2];
                break;
            case R.id.btn_2_4:
                sPressedCaption = sxCaptions[3];
                sPressedPoints = sxPoints[3];
                break;
            case R.id.btn_3_1:
                sPressedCaption = sxCaptions[4];
                sPressedPoints = sxPoints[4];
                break;
            case R.id.btn_3_2:
                sPressedCaption = sxCaptions[5];
                sPressedPoints = sxPoints[5];
                break;
            case R.id.btn_3_3:
                sPressedCaption = sxCaptions[6];
                sPressedPoints = sxPoints[6];
                break;
            case R.id.btn_3_4:
                sPressedCaption = sxCaptions[7];
                sPressedPoints = sxPoints[7];
                break;
            case R.id.btn_4_1:
                sPressedCaption = sxCaptions[8];
                sPressedPoints = sxPoints[8];
                break;
            case R.id.btn_4_2:
                sPressedCaption = sxCaptions[9];
                sPressedPoints = sxPoints[9];
                break;
            case R.id.btn_4_3:
                sPressedCaption = sxCaptions[10];
                sPressedPoints = sxPoints[10];
                break;
            case R.id.btn_4_4:
                sPressedCaption = sxCaptions[11];
                sPressedPoints = sxPoints[11];
                break;
        }

        updateLayout();

        Intent i1 = new Intent();
        i1.setAction("com.example.SendBroadcast.pressed_btn");
        i1.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        i1.putExtra("CATEGORY_PRESSED", sCategory);
        i1.putExtra("STRING_PRESSED", sPressedCaption);
        i1.putExtra("POINTS_PRESSED", sPressedPoints);

        mContext.sendBroadcast(i1);
    }

    private void updateLayout() {

        // Prepare a bundle to update the button text.
        Bundle bundle01 = new Bundle();
        bundle01.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_2_1);
        bundle01.putString(Control.Intents.EXTRA_TEXT, sxCaptions[0]);

        // Prepare a bundle to update the button text.
        Bundle bundle02 = new Bundle();
        bundle02.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_2_2);
        bundle02.putString(Control.Intents.EXTRA_TEXT, sxCaptions[1]);

        // Prepare a bundle to update the button text.
        Bundle bundle03 = new Bundle();
        bundle03.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_2_3);
        bundle03.putString(Control.Intents.EXTRA_TEXT, sxCaptions[2]);

        // Prepare a bundle to update the button text.
        Bundle bundle04 = new Bundle();
        bundle04.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_2_4);
        bundle04.putString(Control.Intents.EXTRA_TEXT, sxCaptions[3]);

        // Prepare a bundle to update the button text.
        Bundle bundle05 = new Bundle();
        bundle05.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_3_1);
        bundle05.putString(Control.Intents.EXTRA_TEXT, sxCaptions[4]);

        // Prepare a bundle to update the button text.
        Bundle bundle06 = new Bundle();
        bundle06.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_3_2);
        bundle06.putString(Control.Intents.EXTRA_TEXT, sxCaptions[5]);

        // Prepare a bundle to update the button text.
        Bundle bundle07 = new Bundle();
        bundle07.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_3_3);
        bundle07.putString(Control.Intents.EXTRA_TEXT, sxCaptions[6]);

        // Prepare a bundle to update the button text.
        Bundle bundle08 = new Bundle();
        bundle08.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_3_4);
        bundle08.putString(Control.Intents.EXTRA_TEXT, sxCaptions[7]);

        // Prepare a bundle to update the button text.
        Bundle bundle09 = new Bundle();
        bundle09.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_4_1);
        bundle09.putString(Control.Intents.EXTRA_TEXT, sxCaptions[8]);

        // Prepare a bundle to update the button text.
        Bundle bundle10 = new Bundle();
        bundle10.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_4_2);
        bundle10.putString(Control.Intents.EXTRA_TEXT, sxCaptions[9]);

        // Prepare a bundle to update the button text.
        Bundle bundle11 = new Bundle();
        bundle11.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_4_3);
        bundle11.putString(Control.Intents.EXTRA_TEXT, sxCaptions[10]);

        // Prepare a bundle to update the button text.
        Bundle bundle12 = new Bundle();
        bundle12.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_4_4);
        bundle12.putString(Control.Intents.EXTRA_TEXT, sxCaptions[11]);

        // Prepare a bundle to update the button text.
        Bundle bundle13 = new Bundle();
        bundle13.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_1_1);
        bundle13.putString(Control.Intents.EXTRA_TEXT, sxCaptions[12]);

        // Prepare a bundle to update the button text.
        Bundle bundle14 = new Bundle();
        bundle14.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_1_2);
        bundle14.putString(Control.Intents.EXTRA_TEXT, sxCaptions[13]);

        // Prepare a bundle to update the button text.
        Bundle bundle15 = new Bundle();
        bundle15.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.btn_1_4);
        bundle15.putString(Control.Intents.EXTRA_TEXT, "\n\n" + Integer.toString(mActiveFace - 2));

        Bundle[] bundleData = new Bundle[15];
        bundleData[0] = bundle01;
        bundleData[1] = bundle02;
        bundleData[2] = bundle03;
        bundleData[3] = bundle04;
        bundleData[4] = bundle05;
        bundleData[5] = bundle06;
        bundleData[6] = bundle07;
        bundleData[7] = bundle08;
        bundleData[8] = bundle09;
        bundleData[9] = bundle10;
        bundleData[10] = bundle11;
        bundleData[11] = bundle12;
        bundleData[12] = bundle13;
        bundleData[13] = bundle14;
        bundleData[14] = bundle15;

        showLayout(R.layout.layout, bundleData);
    }

    /**
     // The sendText method is used to update the text of a single view
     // instead of updating the entire layout.
     sendText(R.id.btn_update_this, caption);

     //sendImage(R.id.image, R.drawable.actions_view_in_phone);

     */

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        mActiveFace = prefs.getInt("iActiveFace", 2);
        sCategory = prefs.getString("category" + Integer.toString(mActiveFace), "");
        sCaptions = prefs.getString("captions" + Integer.toString(mActiveFace), "1;2;3;4;5;6;7;8;9;10;11;12;13;14");
        sPoints = prefs.getString("points" + Integer.toString(mActiveFace), "0;0;0;0;0;0;0;0;0;0;0;0;0;0");

        //!!! careful about the jacka$$es here
        // spaces make it work :-\
        sxCaptions = sCaptions.split(";");
        sxPoints = sPoints.split(";");

        startVibrator(200, 0, 1);
        updateLayout();
    }

    @Override
    public void onSwipe(int direction) {

        // the idea here is to have a linear connection of face objects, maybe up to 5 faces
        // bottom swiping will always signify refresh captions / reset
        // while top swiping always signifies show/hide values

        // swipe up is 0
        // swipe down is 1
        // swipe left is 2
        // swipe right is 3

        if (direction == 2) {
            if(mActiveFace <= 3) {
                mActiveFace++;
            }
        }
        else if (direction == 3) {
            if(mActiveFace >=1) {
                mActiveFace--;
            }
        }
        else if (direction == 1) {
            // from top, down
        }
        else if (direction == 0) {
            // from bottom, up
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        // save the most recent mActiveFace
        prefs.edit()
                .putInt("iActiveFace", mActiveFace)
                .commit();

        // get the corresponding captions
        sCategory = prefs.getString("category" + Integer.toString(mActiveFace), "");
        sCaptions = prefs.getString("captions" + Integer.toString(mActiveFace), "1;2;3;4;5;6;7;8;9;10;11;12;13;14");
        sPoints = prefs.getString("points" + Integer.toString(mActiveFace), "0;0;0;0;0;0;0;0;0;0;0;0;0;0");
        sxCaptions = sCaptions.split(";");

        updateLayout();
    }
}