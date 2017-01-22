package com.example.sonymobile.smartextension.habitaidsw2ext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sonyericsson.extras.liveware.aef.control.Control.Intents;
import com.sonyericsson.extras.liveware.aef.widget.Widget;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlListItem;
import com.sonyericsson.extras.liveware.extension.util.control.ControlObjectClickEvent;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ControlManager extends ControlExtension {
    private static final String HOST_APP_PACKAGE_NAME = "com.sonymobile.smartconnect.smartwatch2";
    private static final String LOG_TAG;

    private static ControlManager sInstance;
    private ControlState mControlState;
    protected BaseControl mCurrentControl;

    private static int iCountDisconnectBugFix = 0;

    public enum ControlState {
        STATE_IDLE,
        STATE_STARTED,
        STATE_FOREGROUND
    }

    static {
        LOG_TAG = ControlManager.class.getName();
        sInstance = null;
    }

    public static synchronized ControlManager getInstance(Context context) {

        ControlManager layoutsControlManager;
        synchronized (ControlManager.class) {
            if (sInstance == null) {
                sInstance = new ControlManager(context, HOST_APP_PACKAGE_NAME);
            }
            layoutsControlManager = sInstance;
        }

        return layoutsControlManager;
    }

    private ControlManager(Context context, String packageName) {
        super(context, packageName);
        this.mCurrentControl = null;
        this.mControlState = ControlState.STATE_IDLE;
    }

    public void startControl(Intent intent) {
        if (this.mCurrentControl == null) {

            this.mCurrentControl = createControl(intent);
            Intent startIntent = new Intent(Intents.CONTROL_START_REQUEST_INTENT);
            startIntent.putExtra(Widget.Intents.EXTRA_AEA_PACKAGE_NAME, this.mContext.getPackageName());
            sendToHostApp(startIntent);

            iCountDisconnectBugFix = 0;
        }
        else {
            // we're assuming here no more than
            // 2 notifications in a row within 5 seconds
            if(iCountDisconnectBugFix > 2) {
                this.mCurrentControl = null;
                this.mControlState = ControlState.STATE_IDLE;
            }
            else {
                this.mCurrentControl.onPause();
                this.mCurrentControl.onStop();
                this.mCurrentControl = createControl(intent);
                this.mCurrentControl.onStart();
                this.mCurrentControl.onResume();

                iCountDisconnectBugFix++;
            }
        }
    }

    private BaseControl createControl(Intent intent) {
        try {
            String className = intent.getComponent().getClassName();
            Log.d(LOG_TAG, "Class name:" + className);
            Constructor<?> ctor = Class.forName(className).getConstructor(new Class[]{Context.class, String.class, Intent.class});
            if (ctor == null) {
                return null;
            }
            Object object = ctor.newInstance(new Object[]{this.mContext, this.mHostAppPackageName, intent});
            if (object instanceof BaseControl) {
                return (BaseControl) object;
            }
            Log.w(LOG_TAG, "Created object not a ManagedControlException");
            return null;
        } catch (SecurityException e) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e);
        } catch (NoSuchMethodException e2) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e2);
        } catch (IllegalArgumentException e3) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e3);
        } catch (InstantiationException e4) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e4);
        } catch (IllegalAccessException e5) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e5);
        } catch (InvocationTargetException e6) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e6);
        } catch (ClassNotFoundException e7) {
            Log.w(LOG_TAG, "ControlManager: Failed in creating control", e7);
        }
        return null;
    }

    public void onMenu() {
        Log.v(LOG_TAG, "onMenu");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onMenu();
        }
    }

    public void onStart() {
        Log.v(LOG_TAG, "onStart");
        if (this.mCurrentControl == null) {
            this.mCurrentControl = createControl(new Intent(this.mContext, ButtonsControl.class));
        }
        this.mControlState = ControlState.STATE_STARTED;
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onStart();
        }
    }

    public void onStop() {
        Log.v(LOG_TAG, "onStop");
        this.mControlState = ControlState.STATE_IDLE;
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onStop();
        }
        //this.mContext.sendBroadcast(new Intent(com.sam.android.app.watchify.utils.Intents.INTENT_ACTION_NOTIFICATIONS_CHANGED));
        this.mCurrentControl = null;
    }

    public void onPause() {
        Log.v(LOG_TAG, "onPause");
        this.mControlState = ControlState.STATE_STARTED;
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onPause();
        }

        stopRequest();
    }

    public void onResume() {
        Log.v(LOG_TAG, "onResume");
        this.mControlState = ControlState.STATE_FOREGROUND;
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onResume();
        }
    }


    public void onTouch(ControlTouchEvent event) {
        Log.v(LOG_TAG, "onTouch");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onTouch(event);
        }
    }

    public void onSwipe(int direction) {
        Log.v(LOG_TAG, "onSwipe");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onSwipe(direction);
        }
    }

    public void onDoAction(int requestCode, Bundle bundle) {
        Log.v(LOG_TAG, "onDoAction");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onDoAction(requestCode, bundle);
        }
    }

    public void onError(int code) {
        Log.d(LOG_TAG, "onError");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onError(code);
        }
    }

    public void onRequestListItem(int layoutReference, int listItemPosition) {
        Log.v(LOG_TAG, "onRequestListItem");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onRequestListItem(layoutReference, listItemPosition);
        }
    }

    public void onListItemClick(ControlListItem listItem, int clickType, int itemLayoutReference) {
        Log.v(LOG_TAG, "onListItemClick");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onListItemClick(listItem, clickType, itemLayoutReference);
        }
    }

    public void onListItemSelected(ControlListItem listItem) {
        Log.v(LOG_TAG, "onListItemSelected");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onListItemSelected(listItem);
        }
    }

    public void onListRefreshRequest(int layoutReference) {
        Log.v(LOG_TAG, "onListRefreshRequest");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onListRefreshRequest(layoutReference);
        }
    }

    public void onObjectClick(ControlObjectClickEvent event) {
        Log.v(LOG_TAG, "onObjectClick");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onObjectClick(event);
        }
    }

    public void onMenuItemSelected(int menuItem) {
        Log.v(LOG_TAG, "onMenuItemSelected");
        if (this.mCurrentControl != null) {
            this.mCurrentControl.onMenuItemSelected(menuItem);
        }
    }

    public void onKey(int action, int keyCode, long timeStamp) {
        Log.v(LOG_TAG, "onKey");
        if (action == 1 && keyCode == 7) {
            Log.d(LOG_TAG, "onKey() - back button intercepted.");
        } else if (action == 1 && keyCode == 8) {
            Log.d(LOG_TAG, "onKey() - options button intercepted.");
        } else if (this.mCurrentControl != null && this.mCurrentControl != null) {
            this.mCurrentControl.onKey(action, keyCode, timeStamp);
        }
    }
}
