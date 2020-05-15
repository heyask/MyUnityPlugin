package com.example.myunityplugin;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

import java.util.logging.Logger;

public class MyUnityPluginBridge {
    private final String TAG = "MyUnityPluginBridge";
    private static MyUnityPluginBridge mInstance;
    private MyUnityPluginCallback mCallback = null;
    private Context mContext;

    public static MyUnityPluginBridge instance() {
        if (mInstance == null) {
            mInstance = new MyUnityPluginBridge();
        }
        return mInstance;
    }


    public MyUnityPluginCallback GetCallbackHandler() {
        return mCallback;
    }

    private void setContext(Context context) {
        Log.w(TAG, "setContext Called!");
        this.mContext = context;
    }

    public boolean Initialize(MyUnityPluginCallback callback) {
        Log.w(TAG, "Initialize Called!");
        if(mCallback == null) {
            mCallback = callback;
            mCallback.OnLoad();
            Log.w(TAG, "MyUnityPluginBridge has initialized!");
            return true;
        } else {
            Log.w(TAG, "MyUnityPluginBridge already initialized!");
            return false;
        }
    }

    private void ShowToast(String toastStr) {
        Toast.makeText(this.mContext, toastStr, Toast.LENGTH_LONG).show();

        MyUnityPluginCallback callback = MyUnityPluginBridge.instance().GetCallbackHandler();
        Log.e(TAG, "callbackTestFunc1 in android1");
        if(callback != null)
        {
            Log.e(TAG, "callbackTestFunc1 in android2");
            callback.OnCallTestFunc1(toastStr);
        }
    }

    private void AndroidVersionCheck(String objName, String objMethod){

        MyUnityPluginCallback callback = MyUnityPluginBridge.instance().GetCallbackHandler();
        Log.e(TAG, "callbackTestFunc2 in android1");
        if(callback != null)
        {
            Log.e(TAG, "callbackTestFunc2 in android2");
            callback.OnCallTestFunc2(11111);
        }

        UnityPlayer.UnitySendMessage(objName, objMethod, "My Android Version: " + Build.VERSION.RELEASE);
    }
}

