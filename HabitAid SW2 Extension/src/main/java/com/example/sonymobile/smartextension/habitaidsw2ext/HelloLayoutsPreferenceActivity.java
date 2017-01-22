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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;


/**
 * The sample control preference activity handles the preferences for the sample
 * control extension.
 */
public class HelloLayoutsPreferenceActivity extends PreferenceActivity {

    private static final int DIALOG_READ_ME = 1;
    private static final int DIALOG_CAPTION_2 = 2;
    private static final int DIALOG_CAPTION_1 = 3;
    private static final int DIALOG_CAPTION0 = 4;
    private static final int DIALOG_CAPTION1 = 5;
    private static final int DIALOG_CAPTION2 = 6;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource.
        addPreferencesFromResource(R.xml.preference);

        // Handle read me.
        Preference preference = findPreference(getText(R.string.preference_key_read_me));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_READ_ME);
                return true;
            }
        });

        // Handle caption-2.
        preference = findPreference(getText(R.string.preference_key_caption_2));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_CAPTION_2);
                return true;
            }
        });

        // Handle caption1.
        preference = findPreference(getText(R.string.preference_key_caption_1));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_CAPTION_1);
                return true;
            }
        });

        // Handle caption0.
        preference = findPreference(getText(R.string.preference_key_caption0));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_CAPTION0);
                return true;
            }
        });

        // Handle caption1.
        preference = findPreference(getText(R.string.preference_key_caption1));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_CAPTION1);
                return true;
            }
        });

        // Handle caption2.
        preference = findPreference(getText(R.string.preference_key_caption2));
        preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_CAPTION2);
                return true;
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch (id) {
            case DIALOG_READ_ME:
                dialog = createReadMeDialog();
                break;
            case DIALOG_CAPTION_2:
                dialog = createCaptionDialog(-2);
                break;
            case DIALOG_CAPTION_1:
                dialog = createCaptionDialog(-1);
                break;
            case DIALOG_CAPTION0:
                dialog = createCaptionDialog(0);
                break;
            case DIALOG_CAPTION1:
                dialog = createCaptionDialog(1);
                break;
            case DIALOG_CAPTION2:
                dialog = createCaptionDialog(2);
                break;
            default:
                Log.w(HelloLayoutsExtensionService.LOG_TAG, "Not a valid dialog id: " + id);
                break;
        }

        return dialog;
    }

    /**
     * Create the Read me dialog.
     *
     * @return the D'ialog
     */
    private Dialog createReadMeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.preference_option_read_me_txt)
                .setTitle(R.string.preference_option_read_me)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton(android.R.string.ok, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    private Dialog createCaptionDialog(int idx) {
        int iXformIdx = 0;
        switch(idx) {
            case -2:
                iXformIdx = 0;
                break;
            case -1:
                iXformIdx = 1;
                break;
            case 0:
                iXformIdx = 2;
                break;
            case 1:
                iXformIdx = 3;
                break;
            case 2:
                iXformIdx = 4;
                break;
            default:
                iXformIdx = 2;
        }

        final int iCaptionIdx = iXformIdx;

        final EditText etInput = new EditText(this);
        etInput.setText("1;2;3;4;5;6;7;8;9;10;11;12;13;14");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Caption")
                .setIcon(android.R.drawable.ic_input_delete)
                .setView(etInput)
                .setPositiveButton("Set", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String sBuf = etInput.getText().toString();
                        int iBuf = 0;

                        iBuf = sBuf.indexOf(":");
                        String sCategory = sBuf.substring(0, iBuf).trim();
                        sBuf = sBuf.substring(iBuf + 1).trim();

                        String[] sxProcess = sBuf.split(";");
                        String sCaptions = "";
                        String sPoints = "";

                        int iBuf2 = 0;
                        for(int i=0; i < 14; i++) {

                            if(i < sxProcess.length) {
                                sBuf = sxProcess[i];
                                if(sBuf.indexOf("(") < 0) {
                                    sCaptions +=  ";" + sBuf.trim();
                                    sPoints += ";0";
                                }
                                else {
                                    iBuf = sBuf.indexOf("(");
                                    iBuf2 = sBuf.indexOf(")");
                                    sCaptions += ";" + sBuf.substring(0,iBuf).trim();
                                    sPoints += ";" + sBuf.substring(iBuf + 1, iBuf2).trim();
                                }
                            }
                            else {
                                sCaptions += "; ";
                                sPoints += "; ";
                            }
                        }

                        sCaptions = sCaptions.substring(1, sCaptions.length());
                        sPoints = sPoints.substring(1, sPoints.length());

                        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        sharedPrefs.edit()
                                .putString("category" + Integer.toString(iCaptionIdx),sCategory)
                                .putString("captions" + Integer.toString(iCaptionIdx),sCaptions)
                                .putString("points" + Integer.toString(iCaptionIdx),sPoints)
                                .commit();
                    }
                }).setNegativeButton("Cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }
}